package controllerIntern;

import dal.InternDao;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Messages;

public class AskMentor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        InternDao dao = new InternDao();
        String action = request.getParameter("action");

        if ("viewMessage".equals(action)) {
            String messageIdStr = request.getParameter("messageId");
            if (messageIdStr != null) {
                try {
                    int messageId = Integer.parseInt(messageIdStr);
                    Messages message = dao.getMessageById(messageId);
                    if (message != null) {
                        request.setAttribute("message", message);
                        String subject = message.getSubject();
                        List<Messages> replies = dao. getSubjectDetail(subject);
                        request.setAttribute("replies", replies);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            request.getRequestDispatcher("AskMentorDetail.jsp").forward(request, response);
        } else if ("search".equals(action)) {
            String user_id = request.getParameter("user_id");
            if (user_id != null) {
               
                String searchSubject = request.getParameter("searchSubject");
                List<Messages> messages = dao.SearchBySubject(searchSubject);

                Map<String, Messages> groupedMessages = new HashMap<>();
                for (Messages msg : messages) {
                    String subject = msg.getSubject();
                    if (!groupedMessages.containsKey(subject)) {
                        groupedMessages.put(subject, msg);
                    }
                }

                request.setAttribute("groupedSubject", groupedMessages.values());
            }
            request.getRequestDispatcher("AskMentor.jsp").forward(request, response);
        } else {
            String user_id = request.getParameter("user_id");
            if (user_id != null) {
                int intern_id = dao.getInternIdByUserId(user_id);
                List<Messages> messages = dao.getListQuestion(intern_id);

                Map<String, Messages> groupedMessages = new HashMap<>();
                for (Messages msg : messages) {
                    String subject = msg.getSubject();
                    if (!groupedMessages.containsKey(subject)) {
                        groupedMessages.put(subject, msg);
                    }
                }

                request.setAttribute("groupedSubject", groupedMessages.values());
            }
            request.getRequestDispatcher("AskMentor.jsp").forward(request, response);
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
        return "AskMentor Servlet";
    }
}
