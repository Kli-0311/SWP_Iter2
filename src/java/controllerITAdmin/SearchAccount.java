package controllerITAdmin;

import dal.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Account;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchUserServlet", urlPatterns = {"/searchUser"})
public class SearchAccount extends HttpServlet {

    private final AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");

        if (username != null && !username.trim().isEmpty()) {
            List<Account> accountList = adminDAO.searchAccountByUsername(username);
            request.setAttribute("list", accountList);
        } else {
            // If no username parameter is provided, show all users
            List<Account> allAccounts = adminDAO.getAllAccount();
            request.setAttribute("list", allAccounts);
        }

        request.getRequestDispatcher("/viewAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

