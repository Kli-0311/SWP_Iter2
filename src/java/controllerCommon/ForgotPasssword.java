/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerCommon;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.Random;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 *
 * @author ADMIN
 */
public class ForgotPasssword extends HttpServlet {

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
        // Lấy email từ request
        String email = request.getParameter("email");
        int otpValue = 0;
        HttpSession session = request.getSession();
        DAO dao = new DAO();

        if (email != null && dao.isEmailExist(email)) {
            // Tạo mã OTP ngẫu nhiên
            Random rand = new Random();
            otpValue = rand.nextInt(1255650);
            // Cấu hình email và mật khẩu của người gửi
            String to = email;  // email người nhận
            final String username = "anhv5203@gmail.com"; // email người gửi
            final String password = "gadm phve hwwr cogd"; // mật khẩu ứng dụng email người gửi

            // Thiết lập các thuộc tính cho phiên email
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

            // Tạo và thông điệp email
            try {
                Message mess = new MimeMessage(mysession);
                mess.setFrom(new InternetAddress(email));
                mess.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                mess.setSubject("OTP for your account");
                mess.setText("Your OTP is: " + otpValue);
                //gửi message
                Transport.send(mess);
            } catch (Exception e) {
            }

            request.setAttribute("message", "OTP is send to your email");
            session.setAttribute("otp", otpValue);
            session.setAttribute("email", email);
            request.getRequestDispatcher("enterOTP.jsp").forward(request, response);
        } else {
            // Email không tồn tại trong cơ sở dữ liệu
            request.setAttribute("message", "Email does not exist");
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
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
