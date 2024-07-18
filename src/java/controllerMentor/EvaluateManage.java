/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerMentor;

import dal.EvaluationDAO;
import dal.InternDao;
import dal.MentorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import models.Account;
import models.Evaluations;
import models.Intern;
import models.Project;
import models.ReportsMentor;

/**
 *
 * @author admin
 */
public class EvaluateManage extends HttpServlet {

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
        request.setAttribute("evaluateManage", "Yes");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String userId = account.getUser_id();
        InternDao idao = new InternDao();
        EvaluationDAO edao = new EvaluationDAO();
        if (service.equals("midterm")) {
            String fullName = request.getParameter("fullName");
            int internId = Integer.parseInt(request.getParameter("internId"));
            String projectCode = request.getParameter("projectCode");
            Evaluations evaluation = (new EvaluationDAO()).getMitermByInternId(internId, projectCode);
            Intern intern = edao.getInternByInternId(internId);
            request.setAttribute("fullName", fullName);
            request.setAttribute("intern", intern);
            request.setAttribute("internId", internId);
            request.setAttribute("evaluation", evaluation);
            request.getRequestDispatcher("Midterm.jsp").forward(request, response);
        }

        if (service.equals("InsertMidTerm")) {
            int internId = Integer.parseInt(request.getParameter("internId"));
            String mentorId = request.getParameter("mentorid");
            String type = request.getParameter("type");
            int attitude = Integer.parseInt(request.getParameter("attitude_score"));
            int soft_skills = Integer.parseInt(request.getParameter("soft_skills_score"));
            int technical_skills = Integer.parseInt(request.getParameter("technical_skills_score"));
            String total = request.getParameter("total");
            String comment = request.getParameter("comment");
            String projectCode = request.getParameter("projectCode");
            String positionCode = request.getParameter("positionCode");
            edao.insertMidtermCore(internId, mentorId, type, attitude, soft_skills, technical_skills, total, comment, projectCode, positionCode);
            request.setAttribute("done", "Done");
            MentorDAO dao = new MentorDAO();
            // lấy list projectname 
            List<Project> projects = dao.getProjectsByUserId(userId);
            request.setAttribute("projects", projects);

            String selectedProject = request.getParameter("selectedProject");
            request.setAttribute("selectedProject", selectedProject);

            List<ReportsMentor> reportsList;
            if (selectedProject != null && !selectedProject.isEmpty() && !selectedProject.equals("all")) {
                reportsList = dao.getReportsByProjectName(selectedProject);
            } else {

                reportsList = dao.getAllReports();
            }
            request.setAttribute("reportsList", reportsList);
            request.getRequestDispatcher("CheckReport.jsp").forward(request, response);

        }

        if (service.equals("Update")) {
            int internId = Integer.parseInt(request.getParameter("internId"));
            String projectCode = request.getParameter("projectCode");
            int evaluationId = Integer.parseInt(request.getParameter("evaluationId"));
            int attitude = Integer.parseInt(request.getParameter("attitude_score"));
            int soft_skills = Integer.parseInt(request.getParameter("soft_skills_score"));
            int technical_skills = Integer.parseInt(request.getParameter("technical_skills_score"));
            String total = request.getParameter("total");
            String comment = request.getParameter("comment");
            edao.updateCore(attitude, soft_skills, technical_skills, total, comment, evaluationId);

            request.setAttribute("done", "Done");
            Evaluations evaluation = edao.getMitermByInternId(internId, projectCode);
            request.setAttribute("evaluation", evaluation);
            request.getRequestDispatcher("Midterm.jsp").forward(request, response);
        }
        if (service.equals("final")) {
            String fullName = request.getParameter("fullName");
            int internId = Integer.parseInt(request.getParameter("internId"));
            String projectCode = request.getParameter("projectCode");
            Evaluations evaluation = (new EvaluationDAO()).getFinalByInternId(internId, projectCode);
            Intern intern = edao.getInternByInternId(internId);
            request.setAttribute("fullName", fullName);
            request.setAttribute("intern", intern);
            request.setAttribute("internId", internId);
            request.setAttribute("finalEvaluation", evaluation);
            request.getRequestDispatcher("Final.jsp").forward(request, response);
        }
        if (service.equals("InsertFinal")) {
            int internId = Integer.parseInt(request.getParameter("internId"));
            String mentorId = request.getParameter("mentorid");
            String type = request.getParameter("type");
            int attitude = Integer.parseInt(request.getParameter("attitude_score"));
            int soft_skills = Integer.parseInt(request.getParameter("soft_skills_score"));
            int technical_skills = Integer.parseInt(request.getParameter("technical_skills_score"));
            String total = request.getParameter("total");
            String comment = request.getParameter("comment");
            String projectCode = request.getParameter("projectCode");
            String positionCode = request.getParameter("positionCode");
            edao.insertFinalCore(internId, mentorId, type, attitude, soft_skills, technical_skills, total, comment, projectCode, positionCode);
            request.setAttribute("done", "Done");
            MentorDAO dao = new MentorDAO();
            // lấy list projectname 
            List<Project> projects = dao.getProjectsByUserId(userId);
            request.setAttribute("projects", projects);

            String selectedProject = request.getParameter("selectedProject");
            request.setAttribute("selectedProject", selectedProject);

            List<ReportsMentor> reportsList;
            if (selectedProject != null && !selectedProject.isEmpty() && !selectedProject.equals("all")) {
                reportsList = dao.getReportsByProjectName(selectedProject);
            } else {

                reportsList = dao.getAllReports();
            }
            request.setAttribute("reportsList", reportsList);
            request.getRequestDispatcher("CheckReport.jsp").forward(request, response);

        }
        if (service.equals("UpdateFinal")) {
            int internId = Integer.parseInt(request.getParameter("internId"));
            String projectCode = request.getParameter("projectCode");
            int evaluationId = Integer.parseInt(request.getParameter("evaluationId"));
            int attitude = Integer.parseInt(request.getParameter("attitude_score"));
            int soft_skills = Integer.parseInt(request.getParameter("soft_skills_score"));
            int technical_skills = Integer.parseInt(request.getParameter("technical_skills_score"));
            String total = request.getParameter("total");
            String comment = request.getParameter("comment");
            edao.updateCore(attitude, soft_skills, technical_skills, total, comment, evaluationId);

            request.setAttribute("done", "Done");
            Evaluations evaluation = edao.getMitermByInternId(internId, projectCode);
            request.setAttribute("finalEvaluation", evaluation);
            request.getRequestDispatcher("Final.jsp").forward(request, response);
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
