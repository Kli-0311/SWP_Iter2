/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerMentor;

import dal.InternDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Enumeration;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;

public class MarkAttendanceServlet extends HttpServlet {

    private static final LocalTime START_TIME = LocalTime.of(8, 0);
    private static final LocalTime END_TIME = LocalTime.of(23, 30);   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        LocalDate currentDate = LocalDate.now();
        InternDao dao = new InternDao();

        // Get the current time
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        // Check if the current time is within the allowed range
        if (currentTime.isBefore(START_TIME) || currentTime.isAfter(END_TIME)) {
            request.setAttribute("message", "Attendance can only be marked between 8 AM and 5 PM.");
            request.getRequestDispatcher("markAttendance.jsp").forward(request, response);
            return;
        }

        // Process attendance marking
        Enumeration<String> parameterNames = request.getParameterNames();
        boolean success = true;

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("status_")) {
                String internIdStr = paramName.split("_")[1];
                int internId = Integer.parseInt(internIdStr);
                String status = request.getParameter(paramName);

                // Check if attendance has already been marked for today
                if (dao.hasAlreadyCheckedIn(internId, Date.valueOf(currentDate))) {
                    success = false;
                    request.setAttribute("message", "Attendance already marked ");
                } else {
                    // Insert the attendance record into the database
                    dao.insertAttendance(internId, Date.valueOf(currentDate), status);
                }
            }
        }

        if (success) {
            request.setAttribute("message", "Attendance marked successfully.");
        }
        
        request.setAttribute("current_time", formattedDate);

        // Redirect or forward to a confirmation page or back to the attendance form
        request.getRequestDispatcher("markAttendance").forward(request, response);
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
