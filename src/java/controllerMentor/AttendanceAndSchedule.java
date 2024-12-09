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
import java.util.List;
import java.util.Map;
import models.Account;
import models.AccountWithInternSchedule;
import models.InternWithInternSchedule;
import models.Interns;
import models.Projects;

/**
 *
 * @author ADMIN
 */
public class AttendanceAndSchedule extends HttpServlet {

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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String userId = account.getUser_id();

        MentorDAO dao = new MentorDAO();
        List<Projects> projects = dao.getProjectsByUserId2(userId);
        request.setAttribute("projects", projects);

        String selectedProject = request.getParameter("selectedProject");
        request.setAttribute("selectedProject", selectedProject);

        List<InternWithInternSchedule> internList;
        if (selectedProject != null && !selectedProject.isEmpty() && !selectedProject.equals("all")) {
            internList = dao.getInternsWithScheduleByProject(selectedProject);
        } else {
            internList = dao.getInternsWithSchedule();
        }

        Map<String, Integer> present_days = dao.getPresentDaysByUserId();
        Map<String, String> positions = dao.getAllPositions();

        request.setAttribute("internList", internList);
        request.setAttribute("present_days", present_days);
        request.setAttribute("positions", positions);

        request.getRequestDispatcher("AttendanceManagement.jsp").forward(request, response);

    }

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
