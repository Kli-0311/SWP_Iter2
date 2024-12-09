/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerMentor;

import dal.EvaluationDAO;
import dal.MentorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Account;
import models.Applications;
import models.CandidateApply;
import models.GradeForInterns;
import models.Interns;
import models.ProjectWithPositions;

/**
 *
 * @author admin
 */
public class ProjectManageByMentor extends HttpServlet {

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
        request.setAttribute("projectManageByMentor", "Yes");
        MentorDAO dao = new MentorDAO();
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            String projectCode = request.getParameter("projectCode");
            ProjectWithPositions projects = dao.getProjectByProjectCode(projectCode);
            List<CandidateApply> listCandidate = dao.getAllCandidateByProjectCode1(projectCode);
            List<Account> listIntern = dao.getAllInternByProjectCode(projectCode);
            request.setAttribute("detailProject", projects);
            request.setAttribute("listCandidate", listCandidate);
            request.setAttribute("listIntern", listIntern);
            request.getRequestDispatcher("ProjectManageByMentor.jsp").forward(request, response);
        }
        if (service.equals("viewGrade")) {
            String projectCode = request.getParameter("projectCode");
            String type = request.getParameter("selectedProject");

            List<GradeForInterns> grade;
            if (type == null || type.equals("all")) {
                grade = (new EvaluationDAO()).getAllGradeForInternByProjectCode(projectCode);
            } else {
                grade = (new EvaluationDAO()).getAllGradeForInternByProjectCodeAndType(projectCode, type);
            }

            request.setAttribute("gradeForIntern", grade);
            request.setAttribute("selectedProject", type); // Save the selected project type to pass to the JSP
            request.getRequestDispatcher("ViewGradeInternByMentor.jsp").forward(request, response);
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
