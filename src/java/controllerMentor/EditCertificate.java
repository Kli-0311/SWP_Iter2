/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerMentor;

import dal.MentorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Certificate;

/**
 *
 * @author NXC2003
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class EditCertificate extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String fileName = null;  // Sửa từ "" thành null để kiểm tra việc không có file tải lên
        String cerImg = null;

        try {
            for (Part part : request.getParts()) {
                String partName = part.getName();
                if (partName.equals("new_cer_img") && part.getSize() > 0) {  // Kiểm tra nếu có file tải lên
                    fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    part.write(uploadPath + File.separator + fileName);
                }
            }

            Map<String, String> formFields = new HashMap<>();
            for (Part part : request.getParts()) {
                String partName = part.getName();
                if (!partName.equals("new_cer_img")) {
                    String fieldValue = request.getParameter(partName);
                    formFields.put(partName, fieldValue);
                }
            }
            String userId = formFields.get("user_id");
            String cerName = formFields.get("cer_name");
            String issueDateStr = formFields.get("issue_date");
            String projectCode = formFields.get("project_code");
            if (fileName != null) {  // Nếu có file tải lên mới thì sử dụng đường dẫn mới
                cerImg = UPLOAD_DIR + File.separator + fileName;
            } else {
                cerImg = formFields.get("cer_img");  // Sử dụng đường dẫn ảnh cũ nếu không có file tải lên mới
            }
            String cerLink = formFields.get("cer_link");
            int internId = Integer.parseInt(formFields.get("intern_id"));
            String senderId = formFields.get("senderId");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date issueDate;
            try {
                issueDate = new Date(dateFormat.parse(issueDateStr).getTime());
                MentorDAO dao = new MentorDAO();

                int cerId = Integer.parseInt(formFields.get("cer_id"));
                dao.editCertificate(cerId, cerName, issueDate, projectCode, cerImg, cerLink, internId, senderId);

                List<Certificate> certificates = dao.getListCertificate(userId);
                request.setAttribute("certificates", certificates);
                request.setAttribute("user_id", userId);
                request.getRequestDispatcher("viewCertificatebyMentor.jsp").forward(request, response);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
