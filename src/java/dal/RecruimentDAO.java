/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Account;
import models.InterviewSchedule;
import models.Messages;
import models.Recruitment;

/**
 *
 * @author admin
 */
public class RecruimentDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void submitRecruiment(String send_id, String receiver_id, String massege, String mess_img, String mess_title, String status, Date date_start, Date date_end) {
        String query = "INSERT INTO [dbo].[Recruitment]\n"
                + "           ([sender_id]\n"
                + "           ,[receiver_id]\n"
                + "           ,[message]\n"
                + "           ,[mess_img]\n"
                + "           ,[mess_title]\n"
                + "           ,[status]\n"
                + "           ,[date_start]\n"
                + "           ,[date_end])\n"
                + "     VALUES(?,?,?,?,?,?,?,?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, send_id);
            ps.setString(2, receiver_id);
            ps.setString(3, massege);
            ps.setString(4, "img/" + mess_img);
            ps.setString(5, mess_title);
            ps.setString(6, status);
            ps.setDate(7, new java.sql.Date(date_start.getTime()));
            ps.setDate(8, new java.sql.Date(date_end.getTime()));
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();;
        }
    }

    public void respondMentor(String status, int messageId) {
        String query = "UPDATE [dbo].[Recruitment] SET [status] = ? WHERE message_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, messageId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Recruitment> getAllMessagesByHR() {
        List<Recruitment> list = new ArrayList<>();
        String query = "Select *\n"
                + "from Recruitment \n"
                + "Where status LIKE 'Pending'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Recruitment(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getDate(10)));
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return list;
    }

    public List<Recruitment> getAllMessagesByMentorId(String userId) {
        List<Recruitment> list = new ArrayList<>();
        String query = "Select *\n"
                + "from Recruitment \n"
                + "Where sender_id LIKE ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Recruitment(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getDate(10)));
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return list;
    }

    public Recruitment getMessageById(int id) {
        String query = "Select *\n"
                + "from Recruitment \n"
                + "Where message_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Recruitment(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getDate(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Recruitment getMessageByReceiverId(String user_id) {
        String query = "SELECT *\n"
                + "FROM Recruitment\n"
                + "WHERE receiver_id LIKE ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Recruitment(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getDate(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(int recruitmentId) {
        String query = "DELETE FROM [dbo].[Recruitment]\n"
                + "      WHERE message_id = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, recruitmentId);
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Account getMentorName(String userId) {
        String query = "SELECT * \n"
                + "FROM Account a\n"
                + "WHERE a.user_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getByte(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
