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
import models.AdminInformation;
import models.CandidateApplyInformation;
import models.InternInformation;
import models.News;
import models.ProjectInformation;
import models.UserInformation;

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
    // hàm đếm xem bảng new có bao nhiêu 
    public int getTotalNews() {
        String query = "SELECT COUNT(*) FROM News;";
        
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);// trong sql nó chỉ trả về 1 kq
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return 0 ;
    }
    // indext là cái số trang mik nhấn vào 
    public List<News> pagingNews(int index) {
        List<News> list = new ArrayList<>();
        String query = "SELECT *from News \n"
                + "ORDER BY news_id \n"
                + "OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY ; ";
        try{
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, (index-1)*5);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new News(
                        rs.getInt(1), // news_id
                        rs.getDate(2), // published_date
                        rs.getString(3), // image_url
                        rs.getString(4), // title
                        rs.getString(5), // contenT
                        rs.getString(6) // user_id 
                ));
            }
        }catch(Exception e){
            
        }
        return list;
    }
    //inter 3
    public List<UserInformation> selectAllUserMentor() {
        List<UserInformation> list = new ArrayList<>();
        String query = "SELECT A.user_id, A.full_name, A.avatar, A.dob, A.gender, A.phone_number, A.is_active, "
                + "STRING_AGG(P.project_name, ',') , "
                + "STRING_AGG(P.project_code, ',') , "
                + "STRING_AGG(CONCAT(CAST(P.project_startday AS VARCHAR), ' - ', CAST(P.project_endday AS VARCHAR)), ',') AS time "
                + "FROM [dbo].[Account] A "
                + "LEFT JOIN [dbo].[Projects] P ON A.user_id = P.mentor_id "
                + "WHERE A.user_id LIKE 'ME%' "
                + "GROUP BY A.user_id, A.full_name, A.avatar, A.dob, A.gender, A.phone_number, A.is_active";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new UserInformation(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<UserInformation> SearchMentorInformation(String SearchMentor) {
        List<UserInformation> list = new ArrayList<>();
        String query = "SELECT A.user_id, \n"
                + "       A.full_name, \n"
                + "       A.avatar, \n"
                + "       A.dob, \n"
                + "       A.gender, \n"
                + "       A.phone_number, \n"
                + "       A.is_active,\n"
                + "       STRING_AGG(P.project_name, ',') AS project_names, \n"
                + "       STRING_AGG(P.project_code, ',') AS project_codes, \n"
                + "       STRING_AGG(CONCAT(CAST(P.project_startday AS VARCHAR), ' - ', CAST(P.project_endday AS VARCHAR)), ',') AS time\n"
                + "FROM [dbo].[Account] A\n"
                + "LEFT JOIN [dbo].[Projects] P ON A.user_id = P.mentor_id\n"
                + "WHERE A.full_name LIKE ? AND A.user_id LIKE 'ME%'\n"
                + "GROUP BY A.user_id, A.full_name, A.avatar, A.dob, A.gender, A.phone_number, A.is_active;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%"+SearchMentor+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new UserInformation(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        
    }
   // list inter information 
    public List<InternInformation> getIntersInforByProjectCode(String projectCode) {
        List<InternInformation> list = new ArrayList<>();
        String query = "SELECT i.intern_id, a.user_id, a.full_name, a.specialization, p.position_name, a.avatar, a.dob, a.gender, a.phone_number, a.is_active \n"
                + "               FROM Interns i \n"
                + "               JOIN Account a ON i.user_id = a.user_id \n"
                + "               JOIN Positions p ON i.position_code = p.position_code \n"
                + "               JOIN Projects pr ON i.project_code = pr.project_code \n"
                + "               WHERE pr.project_name =?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new InternInformation(
                        rs.getInt("intern_id"),
                        rs.getString("user_id"),
                        rs.getString("full_name"),
                        rs.getString("specialization"),
                        rs.getString("position_name"),
                        rs.getString("avatar"),
                        rs.getString("dob"),
                        rs.getString("gender"),
                        rs.getString("phone_number"),
                        rs.getBoolean("is_active")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // list candidate apply 
    public List<CandidateApplyInformation> geCandidateApplyInforByProjectCode(String projectName) {
        List<CandidateApplyInformation> list = new ArrayList<>();
        String query = "SELECT  a.user_id, a.full_name, a.avatar, a.specialization,p.project_name,pos.position_name,a.dob,a.gender, a.phone_number,app.status,app.application_id\n"
                + "FROM Applications app\n"
                + "JOIN Account a ON app.applicant_id = a.user_id\n"
                + "JOIN Projects p ON app.project_code = p.project_code\n"
                + "JOIN Positions pos ON app.position_code = pos.position_code\n"
                + "WHERE p.project_name = ? \n"
                + "ORDER BY p.project_name;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectName);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new CandidateApplyInformation(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // list IT admin 
    public List<AdminInformation> getUsersWithRoleId1() {
        List<AdminInformation> list = new ArrayList<>();
        String query = "SELECT [user_id], [full_name], [dob], [gender], [phone_number], [avatar], [is_active] "
                + "FROM [Project10].[dbo].[Account] "
                + "WHERE [role_id] = 1";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new AdminInformation(
                        rs.getString("user_id"),
                        rs.getString("full_name"),
                        rs.getString("dob"),
                        rs.getString("gender"),
                        rs.getString("phone_number"),
                        rs.getString("avatar"),
                        rs.getBoolean("is_active")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    //list HR information
    public List<AdminInformation> getUsersWithRoleId3() {
        List<AdminInformation> list = new ArrayList<>();
        String query = "SELECT [user_id], [full_name], [dob], [gender], [phone_number], [avatar], [is_active] "
                + "FROM [Project10].[dbo].[Account] "
                + "WHERE [role_id] = 3";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new AdminInformation(
                        rs.getString("user_id"),
                        rs.getString("full_name"),
                        rs.getString("dob"),
                        rs.getString("gender"),
                        rs.getString("phone_number"),
                        rs.getString("avatar"),
                        rs.getBoolean("is_active")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    // list name mentor , name proejct 
    public List<ProjectInformation> getProjectInfoByProjectName(String projectCode) {
    List<ProjectInformation> list = new ArrayList<>();
    String query = "SELECT a.full_name AS fullName, p.project_name AS projectName \n" +
                   "FROM Account a \n" +
                   "JOIN Projects p ON a.user_id = p.mentor_id \n" +
                   "WHERE p.project_name = ?";
    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(query);
        ps.setString(1,projectCode );
        rs = ps.executeQuery();

        while (rs.next()) {
            list.add(new ProjectInformation(
                    rs.getString("fullName"),
                    rs.getString("projectName")
            ));
        }
    } catch (Exception e) {
        e.printStackTrace();
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
    /*public static void main(String[] args) {
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
    }*/
    /*public static void main(String[] args) {
         LabManagerDAO dao  = new LabManagerDAO();
        int news  = dao.getTotalNews();
        System.out.println(news);
    }
}*/
    public static void main(String[] args) {
        LabManagerDAO dao = new LabManagerDAO();
        List<News> list = dao.pagingNews(1);
        for(News o :list ){
            System.out.print(o);
        }
    }
        
}







