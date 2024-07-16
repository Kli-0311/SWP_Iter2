/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerHR;

import dal.HRDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import models.ProjectWithPositions;

/**
 *
 * @author admin
 */
public class CreateRecruiment extends HttpServlet {

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
        request.setAttribute("createRecruiment", "Yes");
        if (service.equals("sendInsertDetail")) {
            List<ProjectWithPositions> list = (new HRDAO()).getAllProjectsWithPositions();
            String projectCode = request.getParameter("project_code");
            String projectName = request.getParameter("project_name");
            String mentorId = request.getParameter("mentor_id");
            String projectImage = request.getParameter("project_img");
            String description = request.getParameter("description");
            String startDayStr = request.getParameter("projectStartDay");
            String endDayStr = request.getParameter("projectEndDay");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String[] positionNames = request.getParameterValues("position_name[]");
            String[] positionCountsStr = request.getParameterValues("position_count[]");

            boolean hasPositions = positionNames != null && positionCountsStr != null;
            HRDAO projectDAO = new HRDAO();
            try {
                Date startDay = dateFormat.parse(startDayStr);
                Date endDay = dateFormat.parse(endDayStr);
                // Kiểm tra mã dự án đã tồn tại chưa
                if (projectDAO.isProjectCodeExists(projectCode)) {
                    request.setAttribute("allProjects", list);
                    request.setAttribute("InsertDone", "Create failed, Project Code exists");
                    request.getRequestDispatcher("RecruimentManage.jsp").forward(request, response);
                    return;
                }
                if (hasPositions) {
                    int[] positionCounts = new int[positionCountsStr.length];
                    for (int i = 0; i < positionCountsStr.length; i++) {
                        positionCounts[i] = Integer.parseInt(positionCountsStr[i]);
                        //check valid positionCount
                        if (positionCounts[i] < 1) {
                            request.setAttribute("allProjects", list);
                            request.setAttribute("InsertDone", "Create failed positionCount invalid");
                            request.getRequestDispatcher("RecruimentManage.jsp").forward(request, response);
                            return;
                        }
                    }
                    projectDAO.addProjectAndPositions(projectCode, projectName, mentorId, projectImage, description, startDay, endDay, positionNames, positionCounts);
                    request.setAttribute("InsertDone", "Create Successfull Project " + projectCode);
                } else {
                    projectDAO.addProject(projectCode, projectName, mentorId, projectImage, description, startDay, endDay);
                    request.setAttribute("InsertDone", "Create Successfull Project " + projectCode);
                }
            } catch (Exception e) {
                request.setAttribute("InsertDone", "Create Failed");
            }
            response.sendRedirect("manageRecruiment");
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
