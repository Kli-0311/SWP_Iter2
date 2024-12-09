/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllerITAdmin;

import dal.AdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Account;

/**
 *
 * @author khanh
 */
@WebServlet(name = "ViewUserInfor", urlPatterns = {"/viewUserInfor"})
public class ViewUserInfor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewUserInfor</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewUserInfor at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("service");
        request.setAttribute("viewUserInfor", "Yes");

        if (service != null) {
            if (service.equals("ViewListHR")) {
                List<Account> listHR = (new AdminDAO()).getAllHR();
                request.setAttribute("listHR", listHR);
            } else if (service.equals("ViewListMentor")) {
                List<Account> listMentor = (new AdminDAO()).getAllMentor();
                request.setAttribute("listMentor", listMentor);
            } else if (service.equals("ViewListIntern")) {
                List<Account> listIntern = (new AdminDAO()).getAllIntern();
                request.setAttribute("listIntern", listIntern);
            } else if (service.equals("ViewListCandidate")) {
                List<Account> listCandidate = (new AdminDAO()).getAllCandidate();
                request.setAttribute("listCandidate", listCandidate);
            }
        }

        request.getRequestDispatcher("/ViewUserInfor.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

