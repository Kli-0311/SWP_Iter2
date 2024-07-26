package controllerIntern;

import dal.AdminDAO;
import dal.DAO;
import dal.InternDao;
import dal.MentorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.Account;
import models.InternSchedule;

public class Attendance extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String wifiIPAddress = (String) session.getAttribute("wifiIPAddress");
        InternDao dao = new InternDao();
        AdminDAO admindao = new AdminDAO();
        MentorDAO mentorDAO = new MentorDAO();
        String user_id = request.getParameter("user_id");
        int intern_id = dao.getInternIdByUserId(user_id);

        String message = request.getParameter("message");
        InternDao internDAO = new InternDao();

        InternSchedule schedule = mentorDAO.getInternSchedule(user_id);

        List<LocalDate> dateList = new ArrayList<>();
        for (LocalDate date = schedule.getStartDate(); !date.isAfter(schedule.getEndDate()); date = date.plusDays(1)) {
            dateList.add(date);
        }

        Map<LocalDate, String> attendanceRecords = mentorDAO.getAttendanceRecords(user_id);

        List<List<LocalDate>> weeks = new ArrayList<>();
        List<LocalDate> week = new ArrayList<>();
        for (LocalDate date : dateList) {
            week.add(date);
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY || date.equals(dateList.get(dateList.size() - 1))) {
                weeks.add(new ArrayList<>(week));
                week.clear();
            }
        }

        int totalPages = (int) Math.ceil((double) weeks.size() / 4);

        String pageStr = request.getParameter("page");
        int page = 1;
        if (pageStr != null) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        List<List<LocalDate>> currentWeeks = new ArrayList<>();
        int startIdx = (page - 1) * 4;
        for (int i = startIdx; i < startIdx + 4 && i < weeks.size(); i++) {
            currentWeeks.add(weeks.get(i));
        }

        java.util.Date currentDate = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(currentDate);
        request.setAttribute("formattedDate", formattedDate);
        request.setAttribute("message", message);
        request.setAttribute("currentWeeks", currentWeeks);
        request.setAttribute("attendanceRecords", attendanceRecords);
        request.setAttribute("internName", internDAO.getInternName(user_id));
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);

        String startIp = admindao.getLatestStartIPAddress();
        String endIp = admindao.getLatestEndIPAddress();
        if (wifiIPAddress == null || !isIpInRange(wifiIPAddress, startIp, endIp)) {
            request.setAttribute("message", "You cannot check-in because you are not connected to the school's WiFi.");
            request.getRequestDispatcher("AttendanceList.jsp").forward(request, response);
            return;
        }

        LocalDate today = LocalDate.now();
        Date startDate = (Date) dao.getInternStartDate(user_id);
        Date endDate = (Date) dao.getInternEndDate(user_id);

        if (today.isBefore(startDate.toLocalDate()) || today.isAfter(endDate.toLocalDate())) {
            request.setAttribute("message", "You can only check-in during your internship period.");
            request.getRequestDispatcher("AttendanceList.jsp").forward(request, response);
            return;
        }

        if (dao.hasAlreadyCheckedIn(intern_id, Date.valueOf(today))) {
            request.setAttribute("message", "You have already checked-in today.");
            request.getRequestDispatcher("AttendanceList.jsp").forward(request, response);
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        LocalTime checkInStartTime = LocalTime.of(10, 0);
        LocalTime checkInEndTime = LocalTime.of(17, 30);

        String status;
        if (now.toLocalTime().isBefore(checkInEndTime) && now.toLocalTime().isAfter(checkInStartTime)) {
            status = "Present";
        } else if (now.toLocalTime().isBefore(checkInStartTime)) {
            request.setAttribute("message", "It's not yet time for check-in.");
            request.getRequestDispatcher("AttendanceList.jsp").forward(request, response);
            return;
        } else {
            request.setAttribute("message", "You have missed the check-in deadline.");
            request.getRequestDispatcher("AttendanceList.jsp").forward(request, response);
            return;
        }

        dao.insertAttendance(intern_id, Date.valueOf(today), status);
        request.setAttribute("message", "Check-in successful. Your status: " + status);
        request.getRequestDispatcher("AttendanceList.jsp").forward(request, response);
    }

    private boolean isIpInRange(String ip, String startIp, String endIp) {
        int[] ipAddr = parseIp(ip);
        int[] startIpAddr = parseIp(startIp);
        int[] endIpAddr = parseIp(endIp);

        for (int i = 0; i < 4; i++) {
            if (ipAddr[i] < startIpAddr[i] || ipAddr[i] > endIpAddr[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] parseIp(String ip) {
        String[] parts = ip.split("\\.");
        int[] ipAddr = new int[4];
        for (int i = 0; i < 4; i++) {
            ipAddr[i] = Integer.parseInt(parts[i]);
        }
        return ipAddr;
    }

    @Override
    public String getServletInfo() {
        return "Attendance Servlet";
    }
}
