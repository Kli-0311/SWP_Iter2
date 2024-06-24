/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerHR;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Notifications;
import dal.HRDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;
import java.text.ParseException;
import models.Account;
import models.ProjectWithPositions;

/**
 *
 * @author admin
 */
public class NotificationManage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String service = request.getParameter("service");
//        request.setAttribute("notificationManage", "Yes");
//        if (service == null) {
//            service = "listAll";
//        }
//        if (service.equals("listAll")) {
//            List<Notifications> notification = (new HRDAO()).getAllInterviewScheduleByHR();
//            request.setAttribute("allInterviewSchedule", notification);
//            request.getRequestDispatcher("InterviewSchedule.jsp").forward(request, response);
//        }
//        if (service.equals("requestInsert")) {
//            List<ProjectWithPositions> listProject = (new HRDAO()).getAllProjectsWithPositions();
//            request.setAttribute("listProject", listProject);
//            request.setAttribute("createInterviewSchedule", "createInterviewSchedule");
//            request.getRequestDispatcher("InterviewSchedule.jsp").forward(request, response);
//        }
//        if (service.equals("sendInsertDetail")) {
//            String sendId = request.getParameter("send_id");
//            String projectCode = request.getParameter("project_code");
//            String dateStr = request.getParameter("date");
//            String timeStr = request.getParameter("time");
//
//            String message = request.getParameter("message");
//            String title = request.getParameter("title");
//            String room = request.getParameter("room");
//
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Time time = Time.valueOf(timeStr);
//            try {
//                Date dateStart = dateFormat.parse(dateStr);
//
//                HRDAO dao = new HRDAO();
//                dao.addInterviewShedule(sendId, projectCode, dateStart, time, message, title, room);
//                request.setAttribute("InsertDone", "Insert Successful!");
//            } catch (ParseException e) {
//                request.setAttribute("InsertDone", "Insert failed: Invalid date or time format.");
//            } catch (Exception e) {
//                request.setAttribute("InsertDone", "Insert failed: " + e.getMessage());
//            }
//
//            response.sendRedirect("notificationManage");
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
