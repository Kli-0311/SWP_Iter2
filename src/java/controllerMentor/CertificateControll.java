package controllerMentor;

import dal.MentorDAO;
import models.Certificate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
        maxFileSize = 1024 * 1024 * 10, 
        maxRequestSize = 1024 * 1024 * 50) 
public class CertificateControll extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MentorDAO dao = new MentorDAO();
        String userId = request.getParameter("user_id");

        List<Certificate> certificates = dao.getListCertificate(userId);
        request.setAttribute("certificates", certificates);

        String action = request.getParameter("action");
        if ("add".equals(action)) {
            request.getRequestDispatcher("addCertificate.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            int cerId = Integer.parseInt(request.getParameter("cer_id"));
            Certificate certificate = dao.getCertificateById(cerId);
            request.setAttribute("certificate", certificate);
            request.getRequestDispatcher("editCertificate.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("viewCertificatebyMentor.jsp").forward(request, response);
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
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        Map<String, String> formFields = new HashMap<>();
        String fileName = "";

        for (Part part : request.getParts()) {
            String partName = part.getName();
            if (partName.equals("cer_img")) {
                fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                part.write(uploadPath + File.separator + fileName);
            } else {
                formFields.put(partName, request.getParameter(partName));
            }
        }

        String user_id = formFields.get("user_id");
        String cerName = formFields.get("cer_name");
        String issueDateStr = formFields.get("issue_date");
        String cerCompany = formFields.get("cer_company");
        String cerImg = UPLOAD_DIR + File.separator + fileName;
        String cerLink = formFields.get("cer_link");
        int internId = Integer.parseInt(formFields.get("intern_id"));
        String senderId = formFields.get("senderId");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date issueDate;
        try {
            issueDate = new Date(dateFormat.parse(issueDateStr).getTime());
            MentorDAO dao = new MentorDAO();

            String action = formFields.get("action");
            if (action == null || action.equals("add")) {
                dao.addCertificate(cerName, issueDate, cerCompany, cerImg, cerLink, internId, senderId);
            } else if (action.equals("edit")) {
                int cerId = Integer.parseInt(formFields.get("cer_id"));
                dao.editCertificate(cerId, cerName, issueDate, cerCompany, cerImg, cerLink, internId, senderId);
            }

            List<Certificate> certificates = dao.getListCertificate(user_id);
            request.setAttribute("certificates", certificates);
            request.setAttribute("user_id", user_id);
            request.getRequestDispatcher("viewCertificatebyMentor.jsp").forward(request, response);
        } catch (ParseException ex) {
            ex.printStackTrace();
            
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
