/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerMentor;

import dal.HRDAO;
import dal.RecruimentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import models.Account;
import models.InterviewSchedule;
import models.Messages;
import models.Recruitment;

/**
 *
 * @author admin
 */
public class SubmitRecruiment extends HttpServlet {

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
        request.setAttribute("submitRecruiment", "Yes");
        Account acc = (Account) request.getSession().getAttribute("account");
        String user_id = acc.getUser_id();
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            List<Recruitment> list = (new RecruimentDAO()).getAllMessagesByMentorId(user_id);
            request.setAttribute("listMessageByMentor", list);
            request.getRequestDispatcher("SubmitRecruiment.jsp").forward(request, response);
        }
        if (service.equals("requestInsert")) {

            request.setAttribute("submit", "submit");
            request.getRequestDispatcher("SubmitRecruiment.jsp").forward(request, response);
        }
        if (service.equals("sendInsertDetail")) {
            String sendId = request.getParameter("send_id");
            String receiveId = request.getParameter("receiverId");
            String massege = request.getParameter("message");
            String img = request.getParameter("img");
            String title = request.getParameter("title");
            String status = request.getParameter("status");
            String dateStartStr = request.getParameter("date_start");
            String dateEndStr = request.getParameter("date_end");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date dateStart = dateFormat.parse(dateStartStr);
                Date dateEnd = dateFormat.parse(dateEndStr);

                RecruimentDAO dao = new RecruimentDAO();
                dao.submitRecruiment(sendId, receiveId, massege, img, title, status, dateStart, dateEnd);
                request.setAttribute("SubmitDone", "Submit Successful!");
            } catch (Exception e) {
                request.setAttribute("SubmitDone", "Submit failed: " + e.getMessage());
            }
            List<Recruitment> list = (new RecruimentDAO()).getAllMessagesByMentorId(user_id);
            request.setAttribute("listMessageByMentor", list);
            request.getRequestDispatcher("SubmitRecruiment.jsp").forward(request, response);
        }
        if (service.equals("delete")) {
            String recruitmentIdStr = request.getParameter("messId");
            int recruitmentId = Integer.parseInt(recruitmentIdStr);
            RecruimentDAO dao = new RecruimentDAO();
            dao.delete(recruitmentId);
            List<Recruitment> list = dao.getAllMessagesByMentorId(user_id);
            request.setAttribute("listMessageByMentor", list);
            request.getRequestDispatcher("SubmitRecruiment.jsp").forward(request, response);
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
