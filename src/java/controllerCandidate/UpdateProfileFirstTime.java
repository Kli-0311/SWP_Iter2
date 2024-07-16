/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerCandidate;

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
public class UpdateProfileFirstTime extends HttpServlet {

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
        DAO dao = new DAO();
        String userName = request.getParameter("userName");
        String fullName = request.getParameter("fullName");
        String dobStr = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String phoneNumber = request.getParameter("phoneNumber");
//        String avatar = request.getParameter("avatar");

        String specialization = request.getParameter("specialization");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");

        
        Part file = request.getPart("avatar");
        String imgFileName = file.getSubmittedFileName();
        
        String uploadPath = "D:/SWP/SWP_Iter2/web/img/" + imgFileName;
        FileOutputStream fos = new FileOutputStream(uploadPath);
        InputStream is = file.getInputStream();
        
        byte[] data= new byte[is.available()];
        is.read(data);
        fos.write(data);
        fos.close();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
             Date dob = dateFormat.parse(dobStr);

            if (!password.equals(repeatPassword)) {
                request.setAttribute("messErrorPass", "Password and Re-Password must be the same.");
                request.getRequestDispatcher("updateProfileFirstTime.jsp").forward(request, response);
                return;
            }

            if (!password.matches("^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,}$")) {
                request.setAttribute("messErrorPass", "Password must be at least 8 characters long, contain at least one uppercase letter and one special character.");
                request.getRequestDispatcher("updateProfileFirstTime.jsp").forward(request, response);
                return;
            }

            Date now = new Date();
            long ageInMillis = now.getTime() - dob.getTime();
            long age = ageInMillis / (1000L * 60 * 60 * 24 * 365);
            if (age < 18) {
                request.setAttribute("messErrorDob", "You must be at least 18 years old.");
                request.getRequestDispatcher("updateProfileFirstTime.jsp").forward(request, response);
                return;
            }

            if (!Pattern.matches("\\d{10}", phoneNumber)) {
                request.setAttribute("messErrorPhone", "Phone number must have 10 digits.");
                request.getRequestDispatcher("updateProfileFirstTime.jsp").forward(request, response);
                return;
            }

            if (imgFileName != null && !imgFileName.isEmpty()) {
                String mimeType = getServletContext().getMimeType(imgFileName);
                if (mimeType == null || !mimeType.startsWith("image")) {
                    request.setAttribute("messErrorAvatar", "Avatar must be an image file.");
                    request.getRequestDispatcher("updateProfileFirstTime.jsp").forward(request, response);
                    return;
                }
            }

            dao.updateAccount(userName, password, fullName, dob, gender, phoneNumber, imgFileName, specialization);
            Account updatedAccount = (Account) session.getAttribute("account");
            updatedAccount.setFull_name(fullName);
            updatedAccount.setDob(dob);
            updatedAccount.setGender(gender);
            updatedAccount.setPhone_number(phoneNumber);
            updatedAccount.setAvatar(imgFileName);
            updatedAccount.setSpecialization(specialization);

            // Redirect to home page
            response.sendRedirect("home");

        } catch (Exception e) {
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
