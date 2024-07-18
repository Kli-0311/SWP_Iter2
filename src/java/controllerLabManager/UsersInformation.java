/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllerLabManager;

import dal.LabManagerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.UserInformation;

/**
 *
 * @author ADM
 */
//inter 3 
public class UsersInformation extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsersInformation</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsersInformation at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
        // Tạo instance của DAO
        LabManagerDAO dao = new LabManagerDAO();
        
        // Lấy danh sách mentors
        List<UserInformation> mentors = dao.selectAllUserMentor();
        
        // Gộp dữ liệu mentors
        Map<String, UserInformation> mergedMentorsMap = new HashMap<>();
        
        for (UserInformation mentor : mentors) {
            if (mergedMentorsMap.containsKey(mentor.getId())) {
                UserInformation existingMentor = mergedMentorsMap.get(mentor.getId());
                // Gộp tên dự án
                String combinedProjects = existingMentor.getNameProjects() + ", " + mentor.getNameProjects();
                existingMentor.setNameProjects(combinedProjects);
                 // Gộp thời gian
                String combinedTime = existingMentor.getTime() + ", " + mentor.getTime();
                existingMentor.setTime(combinedTime);
            } else {
                mergedMentorsMap.put(mentor.getId(), mentor);
            }
        }
        
        List<UserInformation> mergedMentors = new ArrayList<>(mergedMentorsMap.values());
        
        // Đẩy danh sách mentors đã gộp vào request attribute
        request.setAttribute("mentors", mergedMentors);
        
        // Forward request đến JSP để hiển thị
        request.getRequestDispatcher("UserInformation.jsp").forward(request, response);
    }



    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
