package controllerMentor;

import dal.MentorDAO;
import models.Certificate;
import models.Account;
import models.Projects;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import models.Interns;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
        maxFileSize = 1024 * 1024 * 10, 
        maxRequestSize = 1024 * 1024 * 50) 
public class AddCertificate extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
        String fileName = "";
       for (Part part : request.getParts()) {
            String partName = part.getName();
            if (partName.equals("cer_img")) {
                fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                part.write(uploadPath + File.separator + fileName);
            }
        }
        Map<String, String> formFields = new HashMap<>();
        for (Part part : request.getParts()) {
            String partName = part.getName();
            if (!partName.equals("cer_img")) {
                String fieldValue = request.getParameter(partName);
                formFields.put(partName, fieldValue);
            }
        }
        String userId = formFields.get("user_id");
        String cerName = formFields.get("cer_name");
        String issueDateStr = formFields.get("issue_date");
        String projectCode = formFields.get("project_code");
        String cerImg = UPLOAD_DIR + File.separator + fileName;
        String cerLink = formFields.get("cer_link");
        int internId = Integer.parseInt(formFields.get("intern_id"));
        String senderId = formFields.get("senderId");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date issueDate;
        try {
            issueDate = new Date(dateFormat.parse(issueDateStr).getTime());
            
            MentorDAO dao = new MentorDAO();
            dao.addCertificate(cerName, issueDate, projectCode, cerImg, cerLink, internId, senderId);

            List<Certificate> certificates = dao.getListCertificate(userId);
            request.setAttribute("certificates", certificates);
            request.setAttribute("user_id", userId);
            request.getRequestDispatcher("viewCertificatebyMentor.jsp").forward(request, response);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        MentorDAO dao = new MentorDAO();
        String projectCode = request.getParameter("projectCode");
        List<Interns> listIntern = dao.getInternIdbyProject(projectCode);
        request.setAttribute("listIntern", listIntern);
       request.setAttribute("projectCode", projectCode);
         request.getRequestDispatcher("addCertificate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Add Certificate Servlet";
    }
}
