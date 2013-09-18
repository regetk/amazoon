package slet;

import java.io.IOException;
import java.io.PrintWriter;
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
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AmaZlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AmaZlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        
        showPage(request,response);
    }

    
    
   
 private void showPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
String fWord=request.getParameter("fWord");
String sCurr=request.getParameter("sCurr");
String sPage=request.getParameter("page");
int startPage=convertToTen(sPage,13);
int firstItemIdx=((startPage-1)*13)%10;
if(sCurr==null) sCurr="EUR";
if(fWord!=null){
    fWord=fWord.trim();
    if(fWord.length()>0){
        FindIs mil=new FindIs(fWord,startPage,startPage+1);
        FoundItems fiTems=mil.process();
        request.setAttribute("stuff", fiTems);
    }
}
Params fD = new Params();
request.setAttribute("saadetis", fD);

request.getRequestDispatcher("first.jsp").forward(request, response);
    }   
    
    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * 
     * @param sPage - choosen page nr
     * @param i - items in one page
     * @return  -first needed page number in WS query
     */
    private int convertToTen(String sPage, int i) {
        if(sPage==null) return 1;
        try{
        int pNr=Integer.parseInt(sPage);
        pNr=(i*pNr)/10;
        return pNr;
        }
        catch(Exception ex){
        return 1;
        }
             
    }
}
