package controllerIntern;

import dal.InternDao;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Messages;

public class SendReply extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String senderId = request.getParameter("senderId");
        String receiverId = request.getParameter("receiverId");
        String subject = request.getParameter("subject");
        String messageText = request.getParameter("message");
        String messageIdStr = request.getParameter("messageId");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        if (messageIdStr != null) {
            try {
                int messageId = Integer.parseInt(messageIdStr);
                
                Messages message = new Messages();
                message.setSenderId(senderId);
                message.setReceiverId(receiverId);
                message.setSubject(subject);
                message.setMessage(messageText);
                message.setTimestamp(timestamp);
                
                InternDao dao = new InternDao();
                int result = dao.sendQuestion(message);
                
                if (result > 0) {
                    request.setAttribute("statusMessage", "Reply sent successfully.");
                } else {
                    request.setAttribute("statusMessage", "Failed to send reply.");
                }

                
                Messages originalMessage = dao.getMessageById(messageId);
                request.setAttribute("message", originalMessage);
                List<Messages> replies = dao. getSubjectDetail(subject);  
                request.setAttribute("replies", replies);  
                request.getRequestDispatcher("AskMentorDetail.jsp").forward(request, response);
                
            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("error", "Invalid messageId.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "MessageId is required.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
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
        return "Handles sending replies to messages";
    }
}
