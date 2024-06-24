/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllerLabManager;

import dal.LabManagerDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.News;

/**
 *
 * @author ADM
 */
public class ViewNews extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*String newsIdStr = request.getParameter("news_id");
        LabManagerDAO newsDAO = new LabManagerDAO();
        List<News> last = newsDAO.getLastNews();
        

        if (newsIdStr == null) {
            List<News> newsList = newsDAO.getAllNews();
            request.setAttribute("newsListView", newsList);
            request.setAttribute("newsLastView", last);
            request.getRequestDispatcher("news.jsp").forward(request, response);
        } else {
            int newsId = Integer.parseInt(newsIdStr);
            News news = newsDAO.getNewsBynewsId(newsId);
            request.setAttribute("newsDetailsView", news);
            request.setAttribute("newsLastView", last);
            request.getRequestDispatcher("newsDetails.jsp").forward(request, response);
        }
    }*/
        String newsIdStr = request.getParameter("news_id");
        LabManagerDAO newsDAO = new LabManagerDAO();
        List<News> last = newsDAO.getLastNews();

        if (newsIdStr == null) {
            
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);

            // tong so news , và số trang cần 
            int totalNews = newsDAO.getTotalNews();
            int endPage = totalNews / 5;
            if (totalNews % 5 != 0) {
                endPage++;
            }

            List<News> list = newsDAO.pagingNews(index);
            request.setAttribute("ListA", list);
            request.setAttribute("endP", endPage);
            request.setAttribute("tag", index);
            request.setAttribute("newsLastView", last);
            request.getRequestDispatcher("news.jsp").forward(request, response);
        } else {
            // Show details for a specific news item
            int newsId = Integer.parseInt(newsIdStr);
            News news = newsDAO.getNewsBynewsId(newsId);
            request.setAttribute("newsDetailsView", news);
            request.setAttribute("newsLastView", last);
            request.getRequestDispatcher("newsDetails.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
