package controllerITAdmin;

import dal.AdminDAO;
import dal.DBContext;
import dal.ReadFileExcel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name = "CreateAccountCandidate", urlPatterns = {"/createAccountCandidate"})
@MultipartConfig
public class CreateAccountCandidate extends HttpServlet {

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

        if (errorMessages.length() > 0) {
            request.setAttribute("errorMessages", errorMessages.toString());
            request.getRequestDispatcher("displayError.jsp").forward(request, response);
        } else {
            for (Account account : validAccounts) {
                saveAccountToDatabase(account);
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

    private void saveAccountToDatabase(Account account) {
        String sql = "INSERT INTO Account (user_id, username, password, full_name, role_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = new DBContext().getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, account.getUser_id());
            statement.setString(2, account.getUsername());
            statement.setString(3, account.getPassword());
            statement.setString(4, account.getFull_name());
            statement.setInt(5, 6); // Set role_id to 6 by default

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isValidPasswordFormat(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return password.matches(regex);
    }
}
