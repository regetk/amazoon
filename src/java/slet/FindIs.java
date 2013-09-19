package slet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FindIs {

    static Properties seaded = new Properties();
    private static String AWS_ACCESS_KEY_ID;
    private static String AWS_SECRET_KEY;
    private String fWord;
    private int fPage=1;
    private volatile int ePage=1; //it can change in the middle of for cycle
    private int cPages=0;
    
    public int getPagesCount(){return (int) Math.ceil((this.cPages*10)/13.0);}

    public static void readConf() {
        String dPath = "C:\\Users\\Reget.Kalamees\\Documents\\NetBeansProjects\\Amazoon2\\konf.properties";

        try {
            BufferedReader br = new BufferedReader(new FileReader(dPath));
            {
                seaded.load(br);
                AWS_SECRET_KEY = seaded.getProperty("keySecret");
                AWS_ACCESS_KEY_ID = seaded.getProperty("keyId");
            }
        } catch (Exception ex) {

            System.out.println("ei leia");
        }

    }
    /*
     * Use one of the following end-points, according to the region you are
     * interested in:
     * 
     *      US: ecs.amazonaws.com 
     *      CA: ecs.amazonaws.ca 
     *      UK: ecs.amazonaws.co.uk 
     *      DE: ecs.amazonaws.de 
     *      FR: ecs.amazonaws.fr 
     *      JP: ecs.amazonaws.jp
     * 
     */
    private static final String ENDPOINT = "ecs.amazonaws.de";

    public static String makeUrl(int pageNr, String fWord) {
        /*
         * Set up the signed requests helper 
         */
        SignedRequestsHelper helper;
        try {
            helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        String requestUrl = null;
        Map<String, String> params = new HashMap<String, String>();
        params.put("Service", "AWSECommerceService");
        params.put("Version", "2009-03-31");
        params.put("Operation", "ItemSearch");
        params.put("Keywords", fWord);
        params.put("SearchIndex", "All");
        params.put("AssociateTag", "proovitoo-20");
        params.put("ItemPage", String.valueOf(pageNr));
        params.put("ResponseGroup", "Small,OfferSummary");
        requestUrl = helper.sign(params);
        return requestUrl;
    }

   public FindIs(String findW,int startPage,int endPage) {
       this.fWord=findW;
       this.fPage=startPage;
       this.ePage=endPage;
       readConf();
        

    }
   
   /**
    * Joins found XML pages to one XML and unmarshals it to FoundItems collection 
    * @return FoundItems collection
    */

    public FoundItems process() {

        System.out.println("processs ");
        FoundItems fis = new FoundItems();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            ArrayList<Node> allNodes = new ArrayList<Node>();
            //AWS  gives only pages 1-5
            if (this.ePage > 5 || this.ePage < 1) {
                this.ePage = 5;
            }
            if (this.fPage > 5 || this.fPage < 1) {
                this.fPage = 1;
            }
            for (int a = this.fPage; a <= this.ePage; a++) {
                String rUrl = makeUrl(a, this.fWord);
                System.out.println(rUrl);
                Document doc = db.parse(rUrl);
                //Find total pages section
                if(this.cPages==0){
                NodeList totalPages=doc.getElementsByTagName("TotalPages");
                if(totalPages!=null) {
                    Node nTotalPages=totalPages.item(0);
                    String sTP=nTotalPages.getTextContent();
                    if(sTP!=null) this.cPages=Integer.parseInt(sTP);
                    //correct end page - not need to query non-avaiable pages
                    if(this.ePage>this.cPages) this.ePage=this.cPages;
                }
                }
                //Find Items section
                NodeList items = doc.getElementsByTagName("Item");
                if (items == null) {
                    continue;
                }
                for (int n = 0; n < items.getLength(); n++) {
                    allNodes.add(items.item(n));

                }
            }
            Document itemsDoc = db.newDocument();
            Element rootItems = itemsDoc.createElement("Items");
            itemsDoc.appendChild(rootItems);
            for (int a = 0; a < allNodes.size(); a++) {
                Node adNode = itemsDoc.adoptNode(allNodes.get(a));

                rootItems.appendChild(adNode);
            }
            // System.out.println(allNodes.size());
            //
            TransformerFactory tFactory =
                    TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(rootItems);
            // Create a string writer
            StringWriter stringWriter = new StringWriter();
            // Create the result stream for the transform
            StreamResult dStr = new StreamResult(stringWriter);
            transformer.transform(source, dStr);
            //Create stream source for unmarshal
            StringReader str = new StringReader(stringWriter.toString());
            StreamSource ss = new StreamSource(str);

            JAXBContext context = JAXBContext.newInstance(FoundItems.class);
            Unmarshaller um = context.createUnmarshaller();
            fis = (FoundItems) um.unmarshal(ss);
            return fis;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fis;
    }
}
