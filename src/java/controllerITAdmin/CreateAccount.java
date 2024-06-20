package controllerITAdmin;

import dal.AdminDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import models.Account;

/**
 * Servlet to handle account creation.
 */
public class CreateAccount extends HttpServlet {

    /**
     * Processes requests for both HTTP GET and POST methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Setting response content type to text/html with UTF-8 encoding
        response.setContentType("text/html;charset=UTF-8");

        // Creating an instance of AdminDAO to interact with the database
        AdminDAO adminDao = new AdminDAO();

        // Retrieving form parameters
        String user_id = request.getParameter("user_id");
        String username = request.getParameter("username");
        String role_idStr = request.getParameter("role_id");
        String fullName = request.getParameter("full_name");
        String dobStr = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String phoneNumber = request.getParameter("phone_number");
        String avatar = request.getParameter("avatar");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");

        // Check if role_idStr is null or empty
        if (role_idStr == null || role_idStr.isEmpty()) {
            request.setAttribute("errorMessage", "Role ID is required.");
            request.getRequestDispatcher("createAccount.jsp").forward(request, response);
            return;
        }

        // Parsing role_id to an integer
        int role_id = Integer.parseInt(role_idStr);

        // Defining date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // Parsing date of birth from the string
            Date dob = dateFormat.parse(dobStr);

            // Checking if the username ends with the required domain
            if (!username.endsWith("@fpt.edu.vn") && !username.endsWith("@fe.edu.vn")) {
                request.setAttribute("messErrorUsername", "Username must end with @fpt.edu.vn or @fe.edu.vn.");
                request.getRequestDispatcher("createAccount.jsp").forward(request, response);
                return;
            }

            // Checking if the user is at least 18 years old
            Date now = new Date();
            long ageInMillis = now.getTime() - dob.getTime();
            long age = ageInMillis / (1000L * 60 * 60 * 24 * 365);
            if (age < 18) {
                request.setAttribute("messErrorDob", "You must be at least 18 years old.");
                request.getRequestDispatcher("createAccount.jsp").forward(request, response);
                return;
            }

            // Validating phone number format
            if (!Pattern.matches("\\d{10}", phoneNumber)) {
                request.setAttribute("messErrorPhone", "Phone number must have 10 digits.");
                request.getRequestDispatcher("createAccount.jsp").forward(request, response);
                return;
            }

            // Checking if avatar is an image file
            if (avatar != null && !avatar.isEmpty()) {
                String mimeType = getServletContext().getMimeType(avatar);
                if (mimeType == null || !mimeType.startsWith("image")) {
                    request.setAttribute("messErrorAvatar", "Avatar must be an image file.");
                    request.getRequestDispatcher("createAccount.jsp").forward(request, response);
                    return;
                }
            }

            // Checking if password and repeat password match
            if (!password.equals(repeatPassword)) {
                request.setAttribute("messErrorPass", "Password and Re-Password must be the same.");
                request.getRequestDispatcher("createAccount.jsp").forward(request, response);
                return;
            }

            // Validating password complexity
            if (!password.matches("^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,}$")) {
                request.setAttribute("messErrorPass", "Password must be at least 8 characters long, contain at least one uppercase letter and one special character.");
                request.getRequestDispatcher("createAccount.jsp").forward(request, response);
                return;
            }

            // Checking if the username already exists
            Account existingAccount = adminDao.getAccountByUsername(username);
            if (existingAccount != null) {
                request.setAttribute("messErrorUsername", "This username is already registered.");
                request.getRequestDispatcher("createAccount.jsp").forward(request, response);
            } else {
                // Adding the new account to the database
                adminDao.createAccountUser(user_id, username, role_id, fullName, dob, gender, phoneNumber, avatar, password);
                request.setAttribute("successMessage", "Account created successfully");
                request.getRequestDispatcher("createAccount.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // Handling any exceptions that occur during account creation
            request.setAttribute("successMessage", "Account created unsuccessfully");
            request.getRequestDispatcher("createAccount.jsp").forward(request, response);
        }
    }

    // Handles the HTTP GET method.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Handles the HTTP POST method.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Returns a short description of the servlet.
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
