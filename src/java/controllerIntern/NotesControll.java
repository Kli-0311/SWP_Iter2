/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllerIntern;

import dal.InternDao;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Notes;

public class NotesControll extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InternDao dao = new InternDao();
        String user_id = request.getParameter("user_id");
        int intern_id = dao.getInternIdByUserId(user_id);
        List<Notes> notesList = dao.getListNote(intern_id);

        request.setAttribute("notesList", notesList);
        request.getRequestDispatcher("viewProject.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InternDao dao = new InternDao();
        String user_id = request.getParameter("user_id");
        String noteContent = request.getParameter("noteContent");
        String createdAtStr = request.getParameter("createdAt");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int intern_id = dao.getInternIdByUserId(user_id);
            Date createdAt = dateFormat.parse(createdAtStr);

            if (request.getParameter("delete") != null && request.getParameter("delete").equalsIgnoreCase("delete")) {
                dao.deleteNote(intern_id, noteContent, createdAt);
            } else {
                dao.insertNote(intern_id, noteContent, createdAt);
            }

            response.sendRedirect("viewProject?user_id=" + user_id);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}