/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerHR;

import dal.HRDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Account;

/**
 *
 * @author admin
 */
public class DeleteProject extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("service");
        request.setAttribute("deleteControl", "Yes");
        HttpSession session = request.getSession();
        Object o = session.getAttribute("account");
        Account acc = (Account) o;
        if (acc == null || acc.getRole_id() == 1 || acc.getRole_id() == 2 ||acc.getRole_id() == 5 || acc.getRole_id() == 6) {
            response.sendRedirect("error404.jsp");
        } else if(service.equals("deleteProject")){
            String projectCode = request.getParameter("project_code");
            HRDAO pdao = new HRDAO();
            pdao.deleteProject(projectCode);
            response.sendRedirect("manageRecruiment");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
