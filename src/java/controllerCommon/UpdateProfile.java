/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerCommon;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import models.Account;

/**
 *
 * @author ADMIN
 */
@MultipartConfig
public class UpdateProfile extends HttpServlet {

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
        DAO dao = new DAO();
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc == null) {
            // Handle case where account is not found or session is expired
            response.sendRedirect("error404.jsp");
            return;
        }

        String username = acc.getUsername();
        String password = acc.getPassword();
        String full_name = request.getParameter("full_name");
        String dobStr = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String phone_number = request.getParameter("phone_number");
        String avatar = acc.getAvatar(); // Get current avatar from account object
        String specialization = request.getParameter("specialization");

        Part filePart = request.getPart("avatarNew");
        String imgFileName = filePart.getSubmittedFileName();

        if (imgFileName != null && !imgFileName.isEmpty()) {
            String uploadPath = "D:/SE1837_Group2_SWP391/SE1837_Group2_SWP391/web/img/" + imgFileName;
            FileOutputStream fos = new FileOutputStream(uploadPath);
            InputStream is = filePart.getInputStream();
            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
            fos.close();

            avatar = imgFileName;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dob = dateFormat.parse(dobStr);
            Date now = new Date();
            long ageInMillis = now.getTime() - dob.getTime();
            long age = ageInMillis / (1000L * 60 * 60 * 24 * 365);
            if (age < 18) {
                request.setAttribute("messErrorDob", "You must be at least 18 years old.");
                request.getRequestDispatcher("updateProfileFirstTime.jsp").forward(request, response);
                return;
            }

            if (!Pattern.matches("\\d{10}", phone_number)) {
                request.setAttribute("messErrorPhone", "Phone number must have 10 digits.");
                request.getRequestDispatcher("updateProfileFirstTime.jsp").forward(request, response);
                return;
            }

            dao.changeInfoOfUserPage(username, full_name, dob, gender, phone_number, avatar, specialization);

            Account updatedAcc = dao.getAccount(username, password);
            session.setAttribute("account", updatedAcc);
            request.setAttribute("mess", "Update successfully!!!");
        } catch (Exception e) {
        }

        // Forward to the updateProfile.jsp page to display the result
        request.getRequestDispatcher("updateProfile.jsp").forward(request, response);
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
