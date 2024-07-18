package controllerITAdmin;

import java.io.IOException;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class SendEmail extends HttpServlet {

    public static boolean sendEmail(String to, String subject, String content) {
        final String username = "nguyenxuanchienbkn@gmail.com"; 
        final String password = "jdyr rwps uuzj aeqf";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setContent(content, "text/html; charset=UTF-8");
            Transport.send(msg);
            System.out.println("Email sent successfully to " + to);
            return true;
        } catch (MessagingException e) {
            System.out.println("Failed to send email to " + to);
            e.printStackTrace();
            return false;
        }
    }

    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] selectedUsers = request.getParameterValues("selectedUsers");
        List<String> successList = new ArrayList<>();
        List<String> failList = new ArrayList<>();

        if (selectedUsers != null && selectedUsers.length > 0) {
            String subject = "Your DXLab website login account";
            
            for (String username : selectedUsers) {
                String content = "<html><body>"
                                + "<p>Username: " + username + "</p>"
                                + "<p>Password: Admin@123</p>"
                                + "</body></html>";
                
                boolean isSent = sendEmail(username, subject, content);
                if (isSent) {
                    successList.add(username);
                } else {
                    failList.add(username);
                }
            }
        }

        request.setAttribute("successList", successList);
        request.setAttribute("failList", failList);
        
        // Chuyển hướng trở lại trang danh sách tài khoản
        request.getRequestDispatcher("viewAccount").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "SendEmail Servlet for sending emails to selected users";
    }
}