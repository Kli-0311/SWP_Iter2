/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerCandidate;

import dal.CandidateDAO;
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
import models.ProjectPass;
import models.ProjectWithPositions;
import models.Projects;

/**
 *
 * @author admin
 */
public class ViewProjectPass extends HttpServlet {

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
        request.setAttribute("viewProjectPass", "Yes");
        Account acc = (Account) request.getSession().getAttribute("account");
        String user_id = acc.getUser_id();
        CandidateDAO cdao = new CandidateDAO();
        EvaluationDAO edao = new EvaluationDAO();
        MentorDAO mdao = new MentorDAO();
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            List<ProjectPass> list = cdao.getAllProjectPass(user_id);
            request.setAttribute("list", list);
            request.getRequestDispatcher("ViewProjectPass.jsp").forward(request, response);
        }
        if (service.equals("detail")) {
            String projectCode = request.getParameter("projectCode");
            String positionCode = request.getParameter("positionCode");
            String mentorId = request.getParameter("mentorId");
            ProjectWithPositions projects = mdao.getProjectByProjectCode(projectCode);

            request.setAttribute("detailProject", projects);
            request.setAttribute("positionCode", positionCode);
            request.getRequestDispatcher("ViewProjectPass.jsp").forward(request, response);
        }
        if (service.equals("choose")) {
            //insert Interns
            String projectCode = request.getParameter("projectCode");
            String positionCode = request.getParameter("positionCode");
            String mentorId = request.getParameter("mentorId");
            ProjectWithPositions projects = mdao.getProjectByProjectCode(projectCode);
            edao.deleteAllCandidateApplyByUserId(user_id);
            edao.insertIntern(user_id, projectCode, mentorId, positionCode);
            //chuyển role thành intern
            int role = Integer.parseInt(request.getParameter("role"));
            edao.updateRole(role, user_id);
            request.setAttribute("done", "Chuc mung ban da tro thanh Intern cua Project cos ProjectCode = " + projectCode);
            request.getRequestDispatcher("Recruiment.jsp").forward(request, response);
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
