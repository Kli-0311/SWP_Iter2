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
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CreateInternSchedule extends HttpServlet {

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
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String selectedCandidates = request.getParameter("selectedCandidates");

        Date startDate = Date.valueOf(startDateStr);
        Date endDate = Date.valueOf(endDateStr);
        Date currentDate = new Date(System.currentTimeMillis());

        if (startDate.after(endDate)) {
            request.setAttribute("error", "Start date must be before end date.");
            request.getRequestDispatcher("viewCreateInternSchedule").forward(request, response);
            return;
        }

        if (startDate.before(currentDate)) {
            request.setAttribute("error", "Start date cannot be before today's date.");
            request.getRequestDispatcher("viewCreateInternSchedule").forward(request, response);
            return;
        }

        String[] candidateIds = selectedCandidates.split(",");
        AdminDAO adminDAO = new AdminDAO();

        for (String candidateId : candidateIds) {
            if (!adminDAO.hasInternSchedule(candidateId)) {
                adminDAO.createScheduleForCandidate(candidateId, startDate, endDate);
            }
        }

        request.getRequestDispatcher("viewCreateInternSchedule").forward(request, response);
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