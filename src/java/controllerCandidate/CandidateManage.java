/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerCandidate;

import dal.CandidateDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import models.Account;
import models.CandidateApply;

/**
 *
 * @author admin
 */
public class CandidateManage extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CandidateManage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CandidateManage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("service");
        request.setAttribute("CandidateManage", "Yes");
        if (service.equals("applyProject")) {
            String projectCode = request.getParameter("projectCode");
            String positionCode = request.getParameter("positionCode");

            // Lấy đối tượng account từ session
            Account account = (Account) request.getSession().getAttribute("account");
            String userId = account.getUser_id(); // assuming Account has a getUserId() method

            // Save application details to database
            CandidateDAO applicationDAO = new CandidateDAO();
            applicationDAO.saveApplication(userId, projectCode, positionCode);

            // Redirect or forward to a confirmation page
            request.getRequestDispatcher("home").forward(request, response);
        }
//        if(service.equals("viewCandidate")){
//            String positionCode = request.getParameter("positionCode");
//            CandidateDAO adao = new CandidateDAO();
//            List<CandidateApply> viewCandidate = adao.getAllCandidateApplyByPositionCode(positionCode);
//            request.setAttribute("viewCandidate", viewCandidate);
//            request.getRequestDispatcher("DetailProject.jsp").forward(request, response);
//        }
        if(service.equals("viewCandidateAll")){
            String projectCode = request.getParameter("projectCode");
            List<CandidateApply> listCandidate = (new CandidateDAO()).getAllCandidateApplyByProjectCode(projectCode);
            request.setAttribute("listCandidate", listCandidate);
            request.getRequestDispatcher("ViewCandidateApply.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
