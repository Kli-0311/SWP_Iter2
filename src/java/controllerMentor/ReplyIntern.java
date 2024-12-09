/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllerMentor;

import dal.MentorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;
import models.Messages;

/**
 *
 * @author NXC2003
 */
public class ReplyIntern extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
                
                 MentorDAO dao = new MentorDAO();
                int result = dao.sendMessage(message);
                
                if (result > 0) {
                    request.setAttribute("statusMessage", "Reply sent successfully.");
                } else {
                    request.setAttribute("statusMessage", "Failed to send reply.");
                }

                // Redirect back to AskMentorDetail.jsp with updated message and replies
                 Messages originalMessage = dao.getMessageById(messageId);
                request.setAttribute("message", originalMessage);
                List<Messages> replies = dao.getRepliesByMessageId(subject);  // Get replies by subject
                request.setAttribute("replies", replies);  // Set replies as attribute
                request.getRequestDispatcher("MentorMessageDetails.jsp").forward(request, response);
                
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
