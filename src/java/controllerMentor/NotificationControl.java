/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerMentor;

import dal.HRDAO;
import dal.MentorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import models.Account;
import models.Notifications;
import models.Positions;
import models.ProjectWithPositions;
import models.Projects;

/**
 *
 * @author admin
 */
public class NotificationControl extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NotificationControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NotificationControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String service = request.getParameter("service");
        request.setAttribute("notification", "Yes");
        Account acc = (Account) request.getSession().getAttribute("account");
        String user_id = acc.getUser_id();
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            List<Notifications> notification = (new MentorDAO()).getAllNotificationByMentor(user_id);
            request.setAttribute("allNotification", notification);
            request.getRequestDispatcher("Notification.jsp").forward(request, response);
        }
        if (service.equals("requestInsert")) {
            List<Projects> listProject = (new MentorDAO()).getALLProjectByMentor(user_id);
            List<Positions> listPosition = (new MentorDAO()).getAllPositionByMentor(user_id);
            request.setAttribute("listPosition", listPosition);
            request.setAttribute("listProject", listProject);
            request.setAttribute("createNotification", "createNotification");
            request.getRequestDispatcher("Notification.jsp").forward(request, response);
        }
        if (service.equals("sendInsertDetail")) {
            String sendId = request.getParameter("send_id");
            String projectCode = request.getParameter("project_code");
            String positionCode = request.getParameter("position_code");
            String dateStr = request.getParameter("date");
            String timeStr = request.getParameter("time");

            String message = request.getParameter("message");
            String title = request.getParameter("title");
            String room = request.getParameter("room");
            String link = request.getParameter("link");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String timePattern = "^([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";
            if (!timeStr.matches(timePattern)) {
                List<Notifications> list = (new MentorDAO()).getAllNotificationByMentor(user_id);
                request.setAttribute("allNotification", list);
                request.setAttribute("InsertDone", "Create failed: Time format is incorrect! Please use HH:mm:ss format.");
                request.getRequestDispatcher("Notification.jsp").forward(request, response);
                return;
            }
            try {
                Date dateStart = dateFormat.parse(dateStr);
                Time time = Time.valueOf(timeStr);
                MentorDAO dao = new MentorDAO();
                dao.addNotification(sendId, projectCode, positionCode, dateStart, time, message, title, room, link);
                request.setAttribute("InsertDone", "Insert Successful!");
            } catch (ParseException e) {
                request.setAttribute("InsertDone", "Insert failed: Invalid date or time format.");
            } catch (Exception e) {
                request.setAttribute("InsertDone", "Insert failed: " + e.getMessage());
            }

            response.sendRedirect("notification");
        }
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
