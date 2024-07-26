/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerITAdmin;

import dal.AdminDAO;
import dal.ReadFileExcel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import models.Account;

/**
 *
 * @author ADMIN
 */
@MultipartConfig
public class CreateAccountCandidate extends HttpServlet {

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
//        AdminDAO adminDao = new AdminDAO();
//        String user_id = request.getParameter("user_id");
//        String username = request.getParameter("username");
//        String role_idStr = request.getParameter("role_id");
//        try {
//            int role_id = Integer.parseInt(role_idStr);
//            // Kiểm tra định dạng của username
//            if (!username.endsWith("@fpt.edu.vn") && !username.endsWith("@fe.edu.vn")) {
//                request.setAttribute("messErrorUsername", "Username must end with @fpt.edu.vn or @fe.edu.vn.");
//                request.getRequestDispatcher("createAccountCandidate.jsp").forward(request, response);
//                return;
//            }
//            Account existingAccount = adminDao.getAccountByUsername(username);
//            if (existingAccount != null) {
//                request.setAttribute("successMessage", "This username is already registered.");
//                request.getRequestDispatcher("createAccountCandidate.jsp").forward(request, response);
//            } else {
//                adminDao.createAccountCandidate(user_id, username, role_id);
//                request.setAttribute("successMessage", "Account created successfully");
//                request.getRequestDispatcher("createAccountCandidate.jsp").forward(request, response);
//            }
//        } catch (Exception e) {
//            request.setAttribute("successMessage", "Account created unsuccessfully");
//            request.getRequestDispatcher("createAccountCandidate.jsp").forward(request, response);
//        }
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
    
    private static final String UPLOAD_DIRECTORY = "uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        Part filePart = request.getPart("file");
        String fileName = getSubmittedFileName(filePart);
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        StringBuilder errorMessages = new StringBuilder();
        List<Account> validAccounts = validateAndProcessExcel(new File(filePath), errorMessages);
        AdminDAO dao = new AdminDAO();
        if (errorMessages.length() > 0) {
            request.setAttribute("errorMessages", errorMessages.toString());
            request.getRequestDispatcher("displayError.jsp").forward(request, response);
        } else {
            for (Account account : validAccounts) {
                dao.saveAccountToDatabase(account);
            }
            request.setAttribute("accounts", validAccounts);
            request.getRequestDispatcher("displayAccountCandidate.jsp").forward(request, response);
        }
    }

    private String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private List<Account> validateAndProcessExcel(File file, StringBuilder errorMessages) {
        AdminDAO adminDao = new AdminDAO();

        List<Account> listOfAccounts = new ArrayList<>();

        List<Account> accounts = ReadFileExcel.readExcel(file);
        for (Account account : accounts) {
            // Validate each account
            boolean isValid = true;

            // Check if User_id or Username already exists
            if (adminDao.checkAccountExists(account.getUser_id(), account.getUsername())) {
                errorMessages.append("User_id or Username already exists: " + account.getUser_id() + ", " + account.getUsername() + "<br>");
                isValid = false;
            }

            // Check if Username ends with @fpt.edu.vn
            if (!account.getUsername().endsWith("@fpt.edu.vn")) {
                errorMessages.append("Username must end with @fpt.edu.vn: " + account.getUsername() + "<br>");
                isValid = false;
            }

            // Check if Password format is valid
            if (!isValidPasswordFormat(account.getPassword())) {
                errorMessages.append("Invalid password format for user: " + account.getUsername() + "<br>");
                isValid = false;
            }

            if (isValid) {
                listOfAccounts.add(account);
            }
        }

        return listOfAccounts;
    }

    
    private boolean isValidPasswordFormat(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return password.matches(regex);
    }
}
