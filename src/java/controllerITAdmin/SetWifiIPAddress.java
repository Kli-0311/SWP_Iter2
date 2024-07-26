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
import jakarta.servlet.http.HttpSession;
import models.Account;

/**
 *
 * @author ADMIN
 */
public class SetWifiIPAddress extends HttpServlet {

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
         HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        AdminDAO adminDao = new AdminDAO();
        String latestStartIpAddress = adminDao.getLatestStartIPAddress();
        String latestEndIpAddress = adminDao.getLatestEndIPAddress();
        request.setAttribute("latestStartIpAddress", latestStartIpAddress);
        request.setAttribute("latestEndIpAddress", latestEndIpAddress);

        request.getRequestDispatcher("IPAddress.jsp").forward(request, response);
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
         HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String startIpAddress = request.getParameter("startIpAddress");
        String endIpAddress = request.getParameter("endIpAddress");
        String userId = acc.getUser_id();

        AdminDAO adminDao = new AdminDAO();
        adminDao.insertIPAddressRange(userId, startIpAddress, endIpAddress);

        String latestStartIpAddress = adminDao.getLatestStartIPAddress();
        String latestEndIpAddress = adminDao.getLatestEndIPAddress();
        request.setAttribute("latestStartIpAddress", latestStartIpAddress);
        request.setAttribute("latestEndIpAddress", latestEndIpAddress);
        request.setAttribute("message", "WiFi IP Address range set successfully: " + startIpAddress + " - " + endIpAddress);
        request.getRequestDispatcher("IPAddress.jsp").forward(request, response);
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
