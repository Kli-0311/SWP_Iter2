package controllerMentor;

import dal.MentorDAO;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Messages;

public class MentorMessages extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        MentorDAO dao = new MentorDAO();
        String action = request.getParameter("action");

        if ("viewMessage".equals(action)) {
            String messageIdStr = request.getParameter("messageId");
            if (messageIdStr != null) {
                try {
                    int messageId = Integer.parseInt(messageIdStr);
                    Messages message = dao.getMessageById(messageId);
                    if (message != null) {
                        request.setAttribute("message", message);
                        String subject = message.getSubject();  // Set subject from message
                        List<Messages> replies = dao.getRepliesByMessageId(subject);  // Get replies
                        request.setAttribute("replies", replies);  // Set replies as attribute
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            request.getRequestDispatcher("MentorMessageDetails.jsp").forward(request, response);
        } else if ("search".equals(action)) {
            String searchSubject = request.getParameter("searchSubject");
            List<Messages> messages = dao.SearchBySubject(searchSubject);

            // Group messages by subject
            Map<String, Messages> groupedMessages = new HashMap<>();
            for (Messages msg : messages) {
                String subject = msg.getSubject();
                if (!groupedMessages.containsKey(subject)) {
                    groupedMessages.put(subject, msg);
                }
            }

            request.setAttribute("messages", groupedMessages.values());
            request.getRequestDispatcher("MentorMessages.jsp").forward(request, response);
        } else {
            String user_id = request.getParameter("user_id");
            if (user_id != null) {
                List<Messages> messages = dao.getMessagesForMentor(user_id);
                
                // Group messages by subject
                Map<String, Messages> groupedMessages = new HashMap<>();
                for (Messages msg : messages) {
                    String subject = msg.getSubject();
                    if (!groupedMessages.containsKey(subject)) {
                        groupedMessages.put(subject, msg);
                    }
                }
                
                request.setAttribute("messages", groupedMessages.values());
                request.getRequestDispatcher("MentorMessages.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles mentor's view of messages";
    }
}
