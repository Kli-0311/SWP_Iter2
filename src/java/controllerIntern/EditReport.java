package controllerIntern;

import dal.InternDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Account;
import models.Projects;
import models.Reports;

import java.io.IOException;
import java.util.List;

public class EditReport extends HttpServlet {

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
        String reportIdStr = request.getParameter("reportId");
            int reportId = Integer.parseInt(reportIdStr);
            Reports report = dao.getReportById(reportId);
            List<Projects> project = dao.getProjectsOfIntern(acc.getUser_id());
            request.setAttribute("project", project);
            request.setAttribute("report", report);
            request.getRequestDispatcher("editReport.jsp").forward(request, response);
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
        return "Servlet to handle editing reports";
    }
}
