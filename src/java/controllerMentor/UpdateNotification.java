/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerMentor;

import dal.MentorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import models.Account;
import models.Notifications;
import models.Positions;
import java.util.Date;
import java.sql.Time;

/**
 *
 * @author admin
 */
public class UpdateNotification extends HttpServlet {

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
        Account acc = (Account) request.getSession().getAttribute("account");
        String user_id = acc.getUser_id();
        String service = request.getParameter("service");
        request.setAttribute("updateNotification", "Yes");
        MentorDAO dao = new MentorDAO();
        if (service.equals("requestUpdate")) {
            int notificationId = Integer.parseInt(request.getParameter("notificationId"));
            String projectCode = request.getParameter("projectCode");
            List<Positions> listPosition = (new MentorDAO()).getAllPositionByProjectCode(projectCode);
            Notifications product = (new MentorDAO()).getNotificationById(notificationId);
            request.setAttribute("listPosition", listPosition);
            request.setAttribute("notificationUpdate", product);
            request.getRequestDispatcher("UpdateNotification.jsp").forward(request, response);
        }

        if (service.equals("sendUpdateDetail")) {
            int interviewScheduleId = Integer.parseInt(request.getParameter("notificationId"));
            String sendId = request.getParameter("send_id");
            String projectCode = request.getParameter("project_code");
            String positionCode = request.getParameter("position_code");
            String dateStr = request.getParameter("date");
            String publishDateStr = request.getParameter("published_date");
            String timeStr = request.getParameter("time");
            String message = request.getParameter("message");
            String title = request.getParameter("title");
            String room = request.getParameter("room");
            String link = request.getParameter("link");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String timePattern = "^([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";
            if (!timeStr.matches(timePattern)) {
                List<Notifications> notification = (new MentorDAO()).getAllNotificationByMentor(projectCode);
                request.setAttribute("allNotification", notification);
                request.setAttribute("InsertDone", "Update failed: Time format is incorrect! Please use HH:mm:ss format.");
                request.getRequestDispatcher("Notification.jsp").forward(request, response);
                return;
            }
            try {
                Date dateStart = dateFormat.parse(dateStr);
                Date publishDate = dateFormat.parse(publishDateStr);
                Time time = Time.valueOf(timeStr);
                dao.updateNotification(interviewScheduleId, sendId, projectCode, positionCode, message, title, time, dateStart, publishDate, room, link);
                request.setAttribute("InsertDone", "Update Successful!");
            } catch (ParseException e) {
                request.setAttribute("InsertDone", "Update failed!");
            } catch (Exception e) {
                request.setAttribute("InsertDone", "Update failed: " + e.getMessage());
            }
            List<Notifications> list = dao.getAllNotificationByMentor(projectCode);
            request.setAttribute("allNotification", list);
            request.getRequestDispatcher("Notification.jsp").forward(request, response);

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
