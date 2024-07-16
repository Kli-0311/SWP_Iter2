package controllerIntern;

import dal.InternDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import models.Account;
import models.Projects;

public class SubmitReport extends HttpServlet {

     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        InternDao dao = new InternDao();
        List<Projects> project = dao.getProjectsOfIntern(acc.getUser_id());
        request.setAttribute("project", project);

        String weekStr = request.getParameter("week");
        String report = request.getParameter("report");
        String reportLink = request.getParameter("report_link");
        String projectCode = request.getParameter("project_code");
        String reportIds = request.getParameter("reportId");

        try {
            if (weekStr == null || weekStr.isEmpty() || report == null || reportLink == null || projectCode == null) {
                request.setAttribute("error", "All fields are required.");
                request.getRequestDispatcher("submitReport.jsp").forward(request, response);
                return;
            }

            int week = Integer.parseInt(weekStr);
           
            

            int intern_id = dao.getInternIdByUserId(acc.getUser_id());
            String mentorId = dao.getMentoridbyProjectcode(projectCode);

            if (request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("edit")) {
                int reportId = Integer.parseInt(reportIds);
                dao.editReport(reportId, report, reportLink, mentorId, projectCode);
            } else {
                dao.createReport(intern_id, week, report, reportLink, mentorId, projectCode);
            }

            request.getRequestDispatcher("viewReport").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid number format.");
            request.getRequestDispatcher("submitReport.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
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
        return "Servlet to handle submitting reports";
    }
}