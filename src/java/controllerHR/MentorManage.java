/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerHR;

import dal.RecruimentDAO;
import models.Messages;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Account;
import models.Recruitment;

/**
 *
 * @author admin
 */
public class MentorManage extends HttpServlet {

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
        String service = request.getParameter("service");
        request.setAttribute("mentorManage", "Yes");
        Account acc = (Account) request.getSession().getAttribute("account");
        String user_id = acc.getUser_id();
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            List<Recruitment> list = (new RecruimentDAO()).getAllMessagesByHR();
            request.setAttribute("listRecruitment", list);
            request.getRequestDispatcher("ViewRecruimentByHR.jsp").forward(request, response);
        }
        if (service.equals("detail")) {
            String messageIdStr = request.getParameter("messageId");
            int messageId = Integer.parseInt(messageIdStr);
            Recruitment mess = (new RecruimentDAO()).getMessageById(messageId);
            request.setAttribute("message", mess);
            request.getRequestDispatcher("ViewRecruimentByHR.jsp").forward(request, response);
        }
        if (service.equals("respondMentor")) {
            String mentorId = request.getParameter("receiverId");
            String status = request.getParameter("status");
            int recruitmentId = Integer.parseInt(request.getParameter("message_id"));
            RecruimentDAO dao = new RecruimentDAO();
            dao.respondMentor(status, recruitmentId);
            request.setAttribute("done", "Respond Mentor Id " + mentorId + " Success");
            List<Recruitment> list = (new RecruimentDAO()).getAllMessagesByHR();
            request.setAttribute("listRecruitment", list);
            request.getRequestDispatcher("ViewRecruimentByHR.jsp").forward(request, response);

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
