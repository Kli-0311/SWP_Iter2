/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerCommon;

import dal.CandidateDAO;
import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import models.Account;
import models.Applications;

/**
 *
 * @author ADMIN
 */
public class LoginControl extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        DAO dao = new DAO();
        Account acc = dao.login(username, password);
        if (acc == null) {
            request.setAttribute("mess", "Wrong Username or Password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            String role_name = dao.getRoleName(acc.getRole_id());
            if (acc.getIs_active() == 0) {
                request.setAttribute("mess", "The account does not have access rights");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                //lấy projectcode
                CandidateDAO cdao = new CandidateDAO();
                Applications projectCode = cdao.getProjectCodeByApplicantId(acc.getUser_id());
                session.setAttribute("projectCode", projectCode);
                session.setAttribute("account", acc);
                session.setAttribute("role_name", role_name);
                // Lấy địa chỉ IP WiFi và lưu vào session
                String wifiIPAddress = getCurrentWiFiIPAddress(request);
                session.setAttribute("wifiIPAddress", wifiIPAddress);

                if (isFirstLogin(acc)) {
                    response.sendRedirect("updateProfileFirstTime.jsp");
                } else {
                    response.sendRedirect("home");
                }
            }
        }
    }

    private boolean isFirstLogin(Account acc) {
        return acc.getDob() == null;
    }

    public static String getCurrentWiFiIPAddress(HttpServletRequest request) {
        try {
            // lấy danh sách tất cả các NetworkInterface trên máy tính
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            // duyệt qua từng NetworkInterface
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                // lấy danh sách tất cả các InetAddress gắn với NetworkInterface hiện tại
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                // duyệt qua từng InetAddress
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    // kiểm tra nếu địa chỉ là loại IPv4 và không phải địa chỉ loopback (127.0.0.1)
                    if (addr instanceof Inet4Address && !addr.isLoopbackAddress()) {
                        // trả về địa chỉ IP
                        String ipAddress = addr.getHostAddress();
                        return ipAddress;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
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
