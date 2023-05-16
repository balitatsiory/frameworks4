package etu1903.framework.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import etu1903.framework.Mapping;
import etu1903.framework.servlet.Utility;

public class FrontServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */

    // // // ['method']= Mapping{'classname','method'}
    HashMap<String, Mapping> MappingUrls;

    /**
     * 
     */
    @Override
    public void init() {
        try {
            HashMap<String, Mapping> hashMap = new HashMap<String, Mapping>();
            this.MappingUrls = hashMap;

            String packageName = this.getInitParameter("packageName");
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path2 = packageName.replace('.', '/');
            URL resource = classLoader.getResource(path2);
            String pathfile = path2.replace("%20", " ");
            File file = new File(resource.getFile());

            String path1 = file.getAbsolutePath();
            path1 = path1.replace("%20", " ");
            System.out.println(path1);
            this.MappingUrls = Utility.getnameclasse(this.MappingUrls, path1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getinitparam() {

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String path = request.getPathInfo();
            String[] names;
            names = path.split("/");
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Myservlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Myservlet at " + request.getContextPath() + "</h1>");
            out.println("Composants de l url :");

            for (int i = 0; i < names.length; i++) {
                out.println(i + " " + names[i]);
            }
            out.println("<h1>Sprint 3 : </h1> scann package <h2>" + this.getInitParameter("packageName") + "</h2>");
            out.println("<table border=\"1\"><tr><td>Fonction</td><td>Classname</td><td>method</td></tr>");
            for (String i : this.MappingUrls.keySet()) {
                out.println("<tr>");
                out.println("<td>" + i + "</td>");
                out.println("<td>" + this.MappingUrls.get(i).getClassName() + "</td>");
                out.println("<td>" + this.MappingUrls.get(i).getMethod() + "</td>");
                out.println("</tr>");

            }
            out.println("</table> <br>");
            String urlmeth = names[1];
            out.println("url : " + urlmeth);

            for (String i : this.MappingUrls.keySet()) {
                urlmeth = "/" + urlmeth;
                if (urlmeth.compareToIgnoreCase(i) == 0) {
                    out.println(" correspond a la method " + this.MappingUrls.get(i).getMethod() + " dans "
                            + this.MappingUrls.get(i).getClassName());

                }
            }

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            System.out.println("errrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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

}
