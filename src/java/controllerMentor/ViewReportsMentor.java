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
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Account;
import models.Project;
import models.ReportGroup;
import models.ReportsMentor;
import models.WeeklyReport;

/**
 *
 * @author ADM
 *
 *
 * /**
 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
 * methods.
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
public class ViewReportsMentor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewReportsMentor</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewReportsMentor at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String userId = account.getUser_id();

        MentorDAO dao = new MentorDAO();
        List<Project> projects = dao.getProjectsByUserId(userId);
        request.setAttribute("projects", projects);

        String selectedProject = request.getParameter("selectedProject");
        request.setAttribute("selectedProject", selectedProject);

        List<ReportsMentor> reportsList;
        if (selectedProject != null && !selectedProject.isEmpty() && !selectedProject.equals("all")) {
            reportsList = dao.getReportsByProjectName(selectedProject);
        } else {
            reportsList = dao.getAllReports(userId);
        }

        Map<String, ReportGroup> groupedReportsMap = new HashMap<>();
        for (ReportsMentor report : reportsList) {
            String key = report.getInternId() + "_" + report.getProjectCode();
            ReportGroup reportGroup = groupedReportsMap.get(key);
            if (reportGroup == null) {
                reportGroup = new ReportGroup();
                reportGroup.setInternId(report.getInternId());
                reportGroup.setFullName(report.getFullName());
                reportGroup.setProjectCode(report.getProjectCode());
                reportGroup.setPositionCode(report.getPositionCode());
                reportGroup.setWeeklyReports(new ArrayList<>());
                groupedReportsMap.put(key, reportGroup);
            }
            WeeklyReport weeklyReport = new WeeklyReport(report.getWeek(), report.getReportLink());
            reportGroup.getWeeklyReports().add(weeklyReport);
        }

        List<ReportGroup> groupedReportsList = new ArrayList<>(groupedReportsMap.values());
        request.setAttribute("reportsList", groupedReportsList);
        request.getRequestDispatcher("CheckReport.jsp").forward(request, response);

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
