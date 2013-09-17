package slet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author reget.kalamees
 */
@WebServlet(name = "CurrConv", urlPatterns = {"/CurrConv"})
public class CurrConv extends HttpServlet {

  private static String url="http://www.webservicex.net/CurrencyConvertor.asmx/ConversionRate?";  
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
      try {
          String toCurr=request.getParameter("toCurr");
          String link=url+"FromCurrency=EUR&ToCurrency="+toCurr;
          DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
          DocumentBuilder db = dbf.newDocumentBuilder();
          Document doc=db.parse(link);
          NodeList answers=doc.getElementsByTagName("double");
          Node answer=answers.item(0);
          String sAnsw=answer.getTextContent();
          out.println(sAnsw);
          
      } catch (Exception ex) {
          Logger.getLogger(CurrConv.class.getName()).log(Level.SEVERE, null, ex);
          out.println("0");
      }
        
     
    }

   
}
