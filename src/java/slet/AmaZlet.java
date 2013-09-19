package slet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author reget.kalamees
 */
public class AmaZlet extends HttpServlet {

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

        showPage(request, response);
    }

    private void showPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String fWord = request.getParameter("fWord");
        String sCurr = request.getParameter("sCurr");
        String sPage = request.getParameter("page");
        int startPage = convertToTen(sPage, 13);

        if (sCurr == null) {
            sCurr = "EUR";
        }
        if (fWord != null) {
            fWord = fWord.trim();
            if (fWord.length() > 0) {
                FindIs mil = new FindIs(fWord, startPage, startPage + 2); //in rare cases covers 13 elements 3 10-element pages (Ex. 79-91) 
                FoundItems fiTems = mil.process();
                //map 20- element list to 13 elements page
                FoundItems mappedItems = new FoundItems();
                ArrayList<FoundItem> miList = new ArrayList<FoundItem>();
                int firstItemIdx = ((startPage - 1) * 13) % 10;
                int fSize = fiTems.getfItems().size();
                int yj = fSize - firstItemIdx;
                if (yj <= 13) {
                    for (int a = firstItemIdx; a < yj; a++) {
                        miList.add(fiTems.getfItems().get(a));
                    }
                } else {
                    for (int a = firstItemIdx; a < firstItemIdx + 13; a++) {
                        miList.add(fiTems.getfItems().get(a));
                    }
                }

                mappedItems.setfItems(miList);
                //add product list
                request.setAttribute("stuff", mappedItems);
                //ask total pages
                int cPages = mil.getPagesCount();
                //WS allows only 5 pages
                if (cPages > 4) {
                    cPages = 4;
                }
                request.setAttribute("cPages", cPages);

            }
        }
        Params fD = new Params();
        fD.setcUnit(sCurr);
        fD.setfWord(fWord);
        request.setAttribute("saadetis", fD);
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("first.jsp").forward(request, response);
    }

    /**
     *
     * @param sPage - choosen page nr
     * @param i - items in one page
     * @return -first needed page number in WS query
     */
    private int convertToTen(String sPage, int i) {
        if (sPage == null) {
            return 1;
        }
        try {
            int pNr = Integer.parseInt(sPage);
            pNr = (i * pNr) / 10;
            return pNr;
        } catch (Exception ex) {
            return 1;
        }

    }
}
