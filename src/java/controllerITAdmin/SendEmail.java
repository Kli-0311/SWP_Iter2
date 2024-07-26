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
        final String username = "linhtkhe176489@fpt.edu.vn";
        final String password = "eqdl tijc fzec mduv";

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String[] selectedUsers = request.getParameterValues("selectedUsers");
        List<String> successList = new ArrayList<>();
        List<String> failList = new ArrayList<>();

        if (selectedUsers != null && selectedUsers.length > 0) {
            String subject = "Thông báo về tài khoản của bạn trên hệ thống DXLab";

            for (String username : selectedUsers) {
                String content = "<html><body>"
                        + "Chúng tôi vui mừng thông báo rằng tài khoản của bạn đã được tạo thành công trên hệ thống của chúng tôi. Dưới đây là thông tin chi tiết về tài khoản của bạn:<br><br>"
                        + "Email: " + username + "<br>"
                        + "Mật khẩu: Admin@123<br><br>" + "Bạn vui lòng truy cập vào hệ thống và đăng nhập bằng thông tin trên. Sau khi đăng nhập lần đầu, bạn sẽ được yêu cầu đổi mật khẩu để bảo mật tài khoản của mình.<br><br>"
                        + "Hướng dẫn đăng nhập:<br>"
                        + "1. Truy cập vào trang đăng nhập của hệ thống:<br>"
                        + "2. Nhập tên đăng nhập và mật khẩu tạm thời.<br>"
                        + "3. Thay đổi mật khẩu theo yêu cầu của hệ thống.<br><br>"
                        + "Nếu bạn gặp bất kỳ khó khăn nào trong quá trình đăng nhập hoặc cần hỗ trợ thêm, vui lòng liên hệ với chúng tôi qua email hoặc số điện thoại dưới đây:<br>"
                        + "Email hỗ trợ: dxlabfpt@gmail.com<br>"
                        + "Số điện thoại hỗ trợ: 0868 686868<br><br>"
                        + "Chúng tôi rất mong được hợp tác và chúc bạn nhiều thành công với tài khoản mới trên hệ thống của chúng tôi.<br><br>"
                        + "Trân trọng,<br>"
                        + "DX Lab, FPT University Hanoi<br>"
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
        request.getRequestDispatcher("viewAccount").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "SendEmail Servlet for sending emails to selected users";
    }
}
