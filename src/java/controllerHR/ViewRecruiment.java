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
import java.util.List;
import models.ProjectWithPositions;

/**
 *
 * @author admin
 */
public class ViewRecruiment extends HttpServlet {

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

        response.setContentType("text/html;charset=UTF-8");

        // Lấy index trang từ request
        String indexPage = request.getParameter("index");
        int index = 1; // Mặc định là trang đầu tiên
        try {
            if (indexPage != null) {
                index = Integer.parseInt(indexPage);
            }
        } catch (NumberFormatException e) {
            index = 1; // Xử lý ngoại lệ khi indexPage không phải số
        }

        // Lấy danh sách dự án từ DAO
        HRDAO hrDAO = new HRDAO();
        List<ProjectWithPositions> projects = hrDAO.pagingProjects(index);

        // Tính toán số trang cuối cùng
        int totalProjects = hrDAO.getTotalProjects();
        int endPage = totalProjects / 4;
        if (totalProjects % 4 != 0) {
            endPage++;
        }

        // Đặt các thuộc tính cần thiết vào request để hiển thị trên JSP
        request.setAttribute("allProjects", projects);

        request.setAttribute("tag", index);
        request.setAttribute("showSearchProject", "Yes");
        request.setAttribute("endPage", endPage);

        // Chuyển tiếp request đến Recruitment.jsp để hiển thị
        request.getRequestDispatcher("Recruiment.jsp").forward(request, response);

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
