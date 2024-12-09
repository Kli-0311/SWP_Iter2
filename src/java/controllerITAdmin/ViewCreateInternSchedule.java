package controllerITAdmin;

import dal.AdminDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Account;
import models.AccountWithInternSchedule;

public class ViewCreateInternSchedule extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        AdminDAO dao = new AdminDAO();
        
        List<Account> candidatesWithoutSchedule = dao.getCandidatesWithoutSchedule();
        List<AccountWithInternSchedule> candidatesWithSchedule = dao.getCandidatesWithSchedule();
        
        request.setAttribute("listCandidateNoSchedule", candidatesWithoutSchedule);
        request.setAttribute("listCandidateWithSchedule", candidatesWithSchedule);
        
        request.getRequestDispatcher("createInternSchedule.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
