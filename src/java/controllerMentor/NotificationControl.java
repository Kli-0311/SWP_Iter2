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
        String service = request.getParameter("service");
        request.setAttribute("notificationControl", "Yes");
        Account acc = (Account) request.getSession().getAttribute("account");
        String user_id = acc.getUser_id();
        String projectCodeStr = request.getParameter("projectCode");

        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            List<Notifications> notification = (new MentorDAO()).getAllNotificationByMentor(projectCodeStr);
            request.setAttribute("allNotification", notification);
            request.setAttribute("projectCode", projectCodeStr);
            request.getRequestDispatcher("Notification.jsp").forward(request, response);
        }
        if (service.equals("requestInsert")) {
            String projectCode = request.getParameter("projectCode");
            List<Positions> listPosition = (new MentorDAO()).getAllPositionByProjectCode(projectCodeStr);
            request.setAttribute("projectCode", projectCode);
            request.setAttribute("listPosition", listPosition);
            request.setAttribute("createNotification", "createNotification");
            request.getRequestDispatcher("CreateNotification.jsp").forward(request, response);
        }
        if (service.equals("deleteNotification")) {
            String notificationIdStr = request.getParameter("notificationId");
            String projectCode = request.getParameter("projectCode");
            int notificationId = Integer.parseInt(notificationIdStr);
            MentorDAO dao = new MentorDAO();
            dao.deleteNotificationById(notificationId);
            List<Notifications> list = (new MentorDAO()).getAllNotificationByMentor(projectCode);
            request.setAttribute("projectCode", projectCode);
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
