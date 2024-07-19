package controllerITAdmin;

import dal.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Account;

@WebServlet(name = "InactiveAccount", urlPatterns = {"/inactiveAccount"})
public class InactiveAccount extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve form parameters
        String user_id = request.getParameter("user_id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        byte isActive = Byte.parseByte(request.getParameter("is_active"));
        
        // Create an Account object and set its properties from request parameters
        Account account = new Account();
        account.setUser_id(user_id);
        account.setUsername(username);
        account.setPassword(password);
        account.setIs_active(isActive);

        // Call AdminDAO to update the account status
        AdminDAO adminDAO = new AdminDAO();
        adminDAO.inactiveAccount(account);

       
        // Redirect to managerAccount page after update
        response.sendRedirect("viewAccount"); 
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(InactiveAccount.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().println("Error: " + ex.getMessage());
        }
    }

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
    try {
        processRequest(request, response);
    } catch (Exception ex) {
        Logger.getLogger(InactiveAccount.class.getName()).log(Level.SEVERE, null, ex);
        response.getWriter().println("Error: " + ex.getMessage());
    }
}

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

