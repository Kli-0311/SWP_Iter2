/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerITAdmin;

import dal.AdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Account;

/**
 *
 * @author ADMIN
 */
public class CreateAccountCandidate extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        AdminDAO adminDao = new AdminDAO();
        String user_id = request.getParameter("user_id");
        String username = request.getParameter("username");
        String role_idStr = request.getParameter("role_id");
        try {
            int role_id = Integer.parseInt(role_idStr);
            // Kiểm tra định dạng của username
            if (!username.endsWith("@fpt.edu.vn") && !username.endsWith("@fe.edu.vn")) {
                request.setAttribute("messErrorUsername", "Username must end with @fpt.edu.vn or @fe.edu.vn.");
                request.getRequestDispatcher("createAccountCandidate.jsp").forward(request, response);
                return;
            }
            Account existingAccount = adminDao.getAccountByUsername(username);
            if (existingAccount != null) {
                request.setAttribute("successMessage", "This username is already registered.");
                request.getRequestDispatcher("createAccountCandidate.jsp").forward(request, response);
            } else {
                adminDao.createAccountCandidate(user_id, username, role_id);
                request.setAttribute("successMessage", "Account created successfully");
                request.getRequestDispatcher("createAccountCandidate.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("successMessage", "Account created unsuccessfully");
            request.getRequestDispatcher("createAccountCandidate.jsp").forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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

}
