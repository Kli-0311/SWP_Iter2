/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllerITAdmin;

import dal.AdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Account;

/**
 *
 * @author khanh
 */
@WebServlet(name="ViewUserInfor", urlPatterns={"/viewUserInfor"})
public class ViewUserInfor extends HttpServlet {
   
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
            out.println("<title>Servlet ViewUserInfor</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewUserInfor at " + request.getContextPath () + "</h1>");
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
//    Lấy tham số service từ request: Tham số này xác định loại tài khoản nào sẽ được hiển thị.
//Đặt thuộc tính viewUserAccount cho request: Giá trị của thuộc tính này là "Yes".
//Kiểm tra giá trị của service và thực hiện hành động tương ứng:
//ViewListMentor: Lấy danh sách tất cả các tài khoản Mentor từ cơ sở dữ liệu bằng cách gọi phương thức
//getAllMentor() từ AdminDAO, đặt danh sách này vào thuộc tính listMentor của request và 
//chuyển tiếp request đến trang ViewUserAccount.jsp.
//ViewListIntern: Tương tự, nhưng lấy danh sách Intern bằng phương thức getAllIntern() từ AdminDAO.
//ViewListCandidate: Tương tự, nhưng lấy danh sách Candidate bằng phương thức getAllCandidate() từ AdminDAO.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String service = request.getParameter("service");
        request.setAttribute("viewUserInfor", "Yes");

        if (service.equals("ViewListMentor")) {
            List<Account> listMentor = (new AdminDAO()).getAllMentor();
            request.setAttribute("listMentor", listMentor);
            request.getRequestDispatcher("ViewUserInfor.jsp").forward(request, response);

        }
        if (service.equals("ViewListIntern")) {
            List<Account> listIntern = (new AdminDAO()).getAllIntern();
            request.setAttribute("listIntern", listIntern);
            request.getRequestDispatcher("ViewUserInfor.jsp").forward(request, response);

        }
        if (service.equals("ViewListCandidate")) {
            List<Account> listCandidate = (new AdminDAO()).getAllCandidate();
            request.setAttribute("listCandidate", listCandidate);
            request.getRequestDispatcher("ViewUserInfor.jsp").forward(request, response);

        }
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
