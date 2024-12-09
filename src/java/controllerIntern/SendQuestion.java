package controllerIntern;

import dal.InternDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import models.Account;
import models.Messages;
import java.util.List;

public class SendQuestion extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        InternDao dao = new InternDao();

        if (acc != null) {
            List<String> mentorIds = dao.getListMentor(acc.getUser_id());
            request.setAttribute("mentorIds", mentorIds);
        }

        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String senderId = request.getParameter("senderId");
            String receiverId = request.getParameter("receiverId");
            String subject = request.getParameter("subject");
            String messageText = request.getParameter("message");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            Messages message = new Messages();
            message.setSenderId(senderId);
            message.setReceiverId(receiverId);
            message.setSubject(subject);
            message.setMessage(messageText);
            message.setTimestamp(timestamp);

            int result = dao.sendQuestion(message);

            if (result > 0) {
                request.setAttribute("message", "Question sent successfully.");
            } else {
                request.setAttribute("error", "Failed to send question.");
            }
        }

        request.getRequestDispatcher("CreateQuestion.jsp").forward(request, response);
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
        return "SendQuestion servlet for handling sending of questions.";
    }
}