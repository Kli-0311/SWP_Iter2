package controllerHR;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import models.Projects;
import dal.HRDAO;
import java.text.SimpleDateFormat;
import java.util.List;
import models.ProjectWithPositions;
import java.util.Date;
import models.Account;

/**
 *
 * @author admin
 */
public class RecruimentManage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("service");
        request.setAttribute("manageRecruiment", "Yes");
        if (service == null) {
            service = "listAll";
        }
        //view list project
        if (service.equals("listAll")) {
            List<ProjectWithPositions> projects = (new HRDAO()).getAllProjectsWithPositions();
            request.setAttribute("showSearchProject", "Yes");
            request.setAttribute("allProjects", projects);
            request.getRequestDispatcher("RecruimentManage.jsp").forward(request, response);
        }
        //goi form create project
        if (service.equals("requestInsert")) {
            List<Account> listMentor = (new HRDAO()).getAllMentor();
            request.setAttribute("listMentor", listMentor);
            request.setAttribute("insertProject", "insertProject");
            request.getRequestDispatcher("CreateProject.jsp").forward(request, response);
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
