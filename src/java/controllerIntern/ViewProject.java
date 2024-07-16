/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllerIntern;

import dal.InternDao;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Notes;
import models.Projects;

public class ViewProject extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InternDao internDao = new InternDao();
        String user_id = request.getParameter("user_id");
        int intern_id = internDao.getInternIdByUserId(user_id);

        if (intern_id != 0) {
            List<Projects> projectsList = internDao.getProjectsOfIntern(user_id);
            List<Notes> notesList = internDao.getListNote(intern_id);

            request.setAttribute("user_id", user_id);
            request.setAttribute("intern_id", intern_id);
            request.setAttribute("projectsList", projectsList);
            request.setAttribute("notesList", notesList);

            request.getRequestDispatcher("viewProject.jsp").forward(request, response);
        } else {
 
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
}
