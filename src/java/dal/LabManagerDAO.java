/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.News;

/**
 *
 * @author ADM
 */
public class LabManagerDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
// hàm lấy dữ liệu lên trang new jsp va
    public List<News> getAllNews() {
        String query = "SELECT * FROM News;";
        List<News> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new News(
                        rs.getInt(1), // news_id
                        rs.getDate(2), // published_date
                        rs.getString(3), // image_url
                        rs.getString(4), // title
                        rs.getString(5), // contenT
                        rs.getString(6) // user_id 
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
return list;
} 
 // hàm getnews qua userID cho mỗi tài khoản , trang NewsManage
        
    public List<News> getAllNewsByuserID(String userId) {
        String query = "SELECT * FROM News WHERE user_id = ?;";
        List<News> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId); // Thiết lập giá trị cho tham số user_id
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new News(
                        rs.getInt(1), // news_id
                        rs.getDate(2), // published_date
                        rs.getString(3), // image_url
                        rs.getString(4), // title
                        rs.getString(5), // content
                        rs.getString(6) // user_id
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý ngoại lệ

        }
        return list;
    }



    public void deleteNews(int newsId) {
        String query = "DELETE  FROM News\n"
                + "      WHERE news_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, newsId);
            ps.executeUpdate();
        } catch (Exception e) {
             e.printStackTrace();
        }

    }
    //hàm add 
    public void insertNews(String publishedDate, String imageUrl, String title, String content, String userId) {
    String query = "INSERT INTO [dbo].[News]\n"
            + "           ([published_date]\n"
            + "           ,[image_url]\n"
            + "           ,[title]\n"
            + "           ,[content]\n"
            + "           ,[user_id])\n"
            + "     VALUES\n"
            + "           (?,?,?,?,?)";
    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(query);
        ps.setString(1,publishedDate);
        ps.setString(2, "img/"+imageUrl);
        ps.setString(3, title);
        ps.setString(4, content);
        ps.setString(5, userId);
        ps.executeUpdate();
     
    } catch (Exception e) {
         e.printStackTrace();
    } finally {
       
    }
    }
    //hàm load news lên trang loadnews.jsp  và newsDetail 
    public News getNewsBynewsId(int newsId){
        
        String query = "select * from News\n"
                + "where news_id = ?";
        try{
            conn = new DBContext().getConnection();// mo ket noi voi sql 
            ps= conn.prepareStatement(query);
            ps.setInt(1,newsId);
            rs= ps.executeQuery();
            while(rs.next()){
                return new News(rs.getInt(1),
                             rs.getDate(2),
                              rs.getString(3),
                              rs.getString(4),
                              rs.getString(5),
                            rs.getString(6));
            }
        }catch(Exception e ){
            
        }
        return null;
        
      
    }
    // hàm edit news 
    public void editNews(String publishedDate, String imageUrl, String title, String content, String userId, int news_id) {
        String query = "UPDATE [dbo].[News]\n"
                + "   SET [published_date] = ?\n"
                + "      ,[image_url] = ?\n"
                + "      ,[title] = ?\n"
                + "      ,[content] =?\n"
                + "      ,[user_id] = ?\n"
                + " WHERE news_id=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, publishedDate);
            ps.setString(2, "img/"+imageUrl);
            ps.setString(3, title);
            ps.setString(4, content);
            ps.setString(5, userId);
            ps.setInt(6, news_id);
            
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
        }
    } 
    // hàm trả về sản phầm mới nhất 
    public List<News> getLastNews() {
        String query = "select  top 3 * from News \n"
                + "order by news_id  desc";
        List<News> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new News(
                        rs.getInt(1), // news_id
                        rs.getDate(2), // published_date
                        rs.getString(3), // image_url
                        rs.getString(4), // title
                        rs.getString(5), // contenT
                        rs.getString(6) // user_id 
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
return list;

    }

    public List<News> SearchBytitle(String search) {
        String query = "select *from News \n"
                + "where [title] like  ? ";
        List<News> list = new ArrayList<>();


    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(query);
        ps.setString(1,"%"+ search+"%"); // Thiết lập giá trị cho tham số 
        rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new News(
                    rs.getInt(1), // news_id
                    rs.getDate(2), // published_date
                    rs.getString(3), // image_url
                    rs.getString(4), // title
                    rs.getString(5), // content
                    rs.getString(6)  // user_id
            ));
        }
    } catch (Exception e) {
        e.printStackTrace(); // Xử lý ngoại lệ
    
    }
    return list;
}
    


    

    /*public static void main(String[] args) {
        int newsId = 3;
        NewsDAO dao = new NewsDAO();
        dao.deleteNews(newsId);
    }*/
    
    /*public static void main(String[] args) {
        NewsDAO newsDAO = new NewsDAO();
        
        // Sample data to insert
        String publishedDate = "2024-06-15";
        String imageUrl = "http://example.com/image.jpg";
        String title = "Sample News Title huyền ";
        String content = "This is a sample news content.";
        String userId = "ME001";
        
        newsDAO.insertNews(publishedDate, imageUrl, title, content, userId);
        
        System.out.println("News inserted successfully.");
    }*/
    public static void main(String[] args) {
    // Create an instance of the NewsDAO to access the database
        LabManagerDAO newsDAO = new LabManagerDAO();

    // Call the getLastNews method to retrieve the list of latest news
    List<News> newsList = newsDAO.getLastNews();

    // Check if newsList is not empty
    if (!newsList.isEmpty()) {
        // Iterate through the list of news and print details
        for (News news : newsList) {
            System.out.println("News ID: " + news.getNewsId());
            System.out.println("Published Date: " + news.getPublishedDate());
            System.out.println("Image URL: " + news.getImageUrl());
            System.out.println("Title: " + news.getTitle());
            System.out.println("Content: " + news.getContent());
            System.out.println("Author: " + news.getUserId());
            System.out.println(); // Print a blank line between news articles
        }
    } else {
        System.out.println("No news found."); // Handle case where no news is retrieved
    }
}

}



