/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerMentor;

import dal.DAO;
import dal.EvaluationDAO;
import dal.MentorDAO;
import jakarta.mail.Message;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Properties;
import models.Account;
import models.CandidateApply;
import models.ProjectWithPositions;

/**
 *
 * @author admin
 */
public class PassFaildManage extends HttpServlet {

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
        HttpSession session = request.getSession();
        String service = request.getParameter("service");
        request.setAttribute("passFaildManage", "Yes");
        Account acc = (Account) request.getSession().getAttribute("account");
        String mentorName = acc.getFull_name();
        MentorDAO mdao = new MentorDAO();
        EvaluationDAO edao = new EvaluationDAO();
        DAO dao = new DAO();

        //fail
        if (service.equals("fail")) {
            String userId = request.getParameter("userId");
            String projectCode = request.getParameter("projectCode");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");

            String to = email;  // email người nhận
            final String username = "nvdong0902@gmail.com"; // email người gửi
            final String password = "vpyn uzej enaf ucpu"; // mật khẩu ứng dụng email người gửi

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            // Tạo một session email với thông tin xác thực
            jakarta.mail.Session mysession = jakarta.mail.Session.getInstance(props, new jakarta.mail.Authenticator() {
                protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new jakarta.mail.PasswordAuthentication(username, password);
                }
            });

            // Tạo và gửi thông điệp email
            try {
                Message mess = new MimeMessage(mysession);
                mess.setFrom(new InternetAddress(username)); // Đổi thành email người gửi
                mess.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                mess.setSubject("[THONG BAO KET QUA PHONG VAN]");

                String htmlContent = String.format("<!DOCTYPE html>\n"
                        + "<html lang=\"vi\">\n"
                        + "<head>\n"
                        + "    <meta charset=\"UTF-8\">\n"
                        + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                        + "    <title>Thông Báo Kết Quả Phỏng Vấn</title>\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "    <div class=\"container\">\n"
                        + "        <h1>Thông Báo Kết Quả Phỏng Vấn</h1>\n"
                        + "        <p>Kính gửi Anh/Chị <strong>[%s]</strong>,</p>\n"
                        + "        <p>Chúng tôi xin chân thành cảm ơn Anh/Chị đã quan tâm và tham gia phỏng vấn tại công ty <strong>[DX-LAB]</strong>.</p>\n"
                        + "        <p>Chúng tôi thông báo rằng Anh/Chị <strong>không phù hợp</strong> với dự án này chúng tôi.</p>\n"
                        + "        \n"
                        + "            <ul>\n"
                        + "                <li>Chúng tôi rất tiếc phải thông báo rằng Anh/Chị không phù hợp với vị trí này.</li>\n"
                        + "                <li>Bạn hãy đợi thông báo trên hệ thống sẽ có một dự án để bạn có thể tham gia.</li>\n"
                        + "            </ul>\n"
                        + "        \n"
                        + "        <p>Một lần nữa, xin cảm ơn Anh/Chị đã dành thời gian tham gia phỏng vấn với chúng tôi. Chúc Anh/Chị nhiều thành công trong tương lai.</p>\n"
                        + "        <p>Trân trọng,</p>\n"
                        + "        <p><strong>[%s]</strong></p>\n"
                        + "        <p><strong>[Mentor]</strong></p>\n"
                        + "        <p><strong>[DX-LAB]</strong></p>\n"
                        + "        <div class=\"footer\">\n"
                        + "            <p>&copy; 2024 [DX-LAB]. All rights reserved.</p>\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + "</body>\n"
                        + "</html>", fullname, mentorName);

                mess.setContent(htmlContent, "text/html; charset=utf-8");

                // Gửi message
                Transport.send(mess);
            } catch (Exception e) {
                e.printStackTrace();
            }

            edao.failCandidateApply(userId, projectCode);
            ProjectWithPositions projects = mdao.getProjectByProjectCode(projectCode);
            List<CandidateApply> listCandidate = mdao.getAllCandidateByProjectCode1(projectCode);
            List<Account> listIntern = mdao.getAllInternByProjectCode(projectCode);
            request.setAttribute("detailProject", projects);
            request.setAttribute("listCandidate", listCandidate);
            request.setAttribute("listIntern", listIntern);
            request.getRequestDispatcher("ProjectManageByMentor.jsp").forward(request, response);
        }

        //pass
        if (service.equals("pass")) {
            //insert Interns
            String userId = request.getParameter("userId");
            String projectCode = request.getParameter("projectCode");
            String status = request.getParameter("status");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            edao.updateStatus(status, userId, projectCode);

            String to = email;  // email người nhận
            final String username = "nvdong0902@gmail.com"; // email người gửi
            final String password = "vpyn uzej enaf ucpu"; // mật khẩu ứng dụng email người gửi

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            // Tạo một session email với thông tin xác thực
            jakarta.mail.Session mysession = jakarta.mail.Session.getInstance(props, new jakarta.mail.Authenticator() {
                protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new jakarta.mail.PasswordAuthentication(username, password);
                }
            });

            // Tạo và gửi thông điệp email
            try {
                Message mess = new MimeMessage(mysession);
                mess.setFrom(new InternetAddress(username)); // Đổi thành email người gửi
                mess.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                mess.setSubject("[THONG BAO KET QUA PHONG VAN]");

                String htmlContent = String.format("<!DOCTYPE html>\n"
                        + "<html lang=\"vi\">\n"
                        + "<head>\n"
                        + "    <meta charset=\"UTF-8\">\n"
                        + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                        + "    <title>Thông Báo Kết Quả Phỏng Vấn</title>\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "    <div class=\"container\">\n"
                        + "        <h1>Thông Báo Kết Quả Phỏng Vấn</h1>\n"
                        + "        <p>Kính gửi Anh/Chị <strong>%s</strong>,</p>\n"
                        + "        <p>Chúng tôi xin chân thành cảm ơn Anh/Chị đã quan tâm và tham gia phỏng vấn tại công ty <strong>DX-LAB</strong>.</p>\n"
                        + "        <p>Chúng tôi vui mừng thông báo rằng Anh/Chị đã <strong>vượt qua</strong> vòng phỏng vấn tại công ty chúng tôi.</p>\n"
                        + "        <ul>\n"
                        + "            <li>Vui lòng làm theo hướng dẫn để tiếp tục quá trình tuyển dụng.</li>\n"
                        + "            <li>Chọn <strong>Recruitment</strong> -> <strong>Project pass</strong> -> chọn dự án mà Anh/Chị muốn tham gia nhất (nếu vượt qua nhiều dự án).</li>\n"
                        + "            <li>Theo dõi lịch làm việc qua dự án mà Anh/Chị đã chọn.</li>\n"
                        + "        </ul>\n"
                        + "        <p>Một lần nữa, xin cảm ơn Anh/Chị đã dành thời gian tham gia phỏng vấn với chúng tôi. Chúng tôi chúc Anh/Chị nhiều thành công trong tương lai.</p>\n"
                        + "        <p>Trân trọng,</p>\n"
                        + "        <p><strong>%s</strong></p>\n"
                        + "        <p><strong>Mentor</strong></p>\n"
                        + "        <p><strong>DX-LAB</strong></p>\n"
                        + "        <div class=\"footer\">\n"
                        + "            <p>&copy; 2024 DX-LAB. All rights reserved.</p>\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + "</body>\n"
                        + "</html>", fullname, mentorName);

                mess.setContent(htmlContent, "text/html; charset=utf-8");

                // Gửi message
                Transport.send(mess);
            } catch (Exception e) {
                e.printStackTrace();
            }

            ProjectWithPositions projects = mdao.getProjectByProjectCode(projectCode);
            List<CandidateApply> listCandidate = mdao.getAllCandidateByProjectCode1(projectCode);
            List<Account> listIntern = mdao.getAllInternByProjectCode(projectCode);
            request.setAttribute("detailProject", projects);
            request.setAttribute("listCandidate", listCandidate);
            request.setAttribute("listIntern", listIntern);
            request.getRequestDispatcher("ProjectManageByMentor.jsp").forward(request, response);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
