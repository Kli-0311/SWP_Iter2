/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerIntern;

import dal.InternDao;
import dal.LabManagerDAO;
import dal.MentorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import models.Account;
import models.Attendance;
import models.AttendanceList;
import models.InternSchedule;
import models.ProjectAttendanceByIntern;
import models.ProjectInformation;

/**
 *
 * @author ADM
 */
public class ViewAttendanceList extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userId = request.getParameter("user_id");
        String message = request.getParameter("message");
        MentorDAO adminDAO = new MentorDAO();
        InternDao internDAO = new InternDao();

        InternSchedule schedule = adminDAO.getInternSchedule(userId);

        List<LocalDate> dateList = new ArrayList<>();
        for (LocalDate date = schedule.getStartDate(); !date.isAfter(schedule.getEndDate()); date = date.plusDays(1)) {
            dateList.add(date);
        }

        Map<LocalDate, String> attendanceRecords = adminDAO.getAttendanceRecords(userId);

        List<List<LocalDate>> weeks = new ArrayList<>();
        List<LocalDate> week = new ArrayList<>();
        for (LocalDate date : dateList) {
            week.add(date);
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY || date.equals(dateList.get(dateList.size() - 1))) {
                weeks.add(new ArrayList<>(week));
                week.clear();
            }
        }

        // Calculate total pages (4 weeks per page)
        int totalPages = (int) Math.ceil((double) weeks.size() / 4);

        // Get current page from request
        String pageStr = request.getParameter("page");
        int page = 1;
        if (pageStr != null) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        // Get the current 4 weeks' dates
        List<List<LocalDate>> currentWeeks = new ArrayList<>();
        int startIdx = (page - 1) * 4;
        for (int i = startIdx; i < startIdx + 4 && i < weeks.size(); i++) {
            currentWeeks.add(weeks.get(i));
        }

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(currentDate);

        request.setAttribute("formattedDate", formattedDate);
        request.setAttribute("message", message);
        request.setAttribute("currentWeeks", currentWeeks);
        request.setAttribute("attendanceRecords", attendanceRecords);
        request.setAttribute("internName", internDAO.getInternName(userId));
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("AttendanceList.jsp").forward(request, response);
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

   
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
