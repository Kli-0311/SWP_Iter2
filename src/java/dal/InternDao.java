/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import controllerIntern.Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Account;
import models.Attendance;

import models.Interns;
import models.MemberWithPosition;

import models.Messages;
import models.Notes;
import models.Notifications;
import models.Projects;
import models.Reports;

/**
 *
 * @author NXC2003
 */
public class InternDao {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public int getInternIdByUserId(String user_id) {
        String query = "Select intern_id from Interns Where user_id =?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Projects> getProjectsOfIntern(String user_id) {
        String query = "SELECT * FROM Projects p \n"
                + "  JOIN Interns i ON p.project_code = i.project_code \n"
                + "   WHERE user_id = ?";
        List<Projects> projectsList = new ArrayList<>();

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Projects project = new Projects(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7)
                );
                projectsList.add(project);
            }
            return projectsList;
        } catch (Exception e) {

        }
        return null;
    }

    public List<Notes> getListNote(int internId) {
        List<Notes> list = new ArrayList<>();
        String query = "SELECT * FROM Notes n "
                + "WHERE intern_id = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, internId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Notes n = new Notes(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getDate(4)
                );
                list.add(n);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public int insertNote(int internId, String noteContent, Date createdAt) {
        String query = "INSERT INTO [dbo].[Notes] ([intern_id], [note], [created_at]) VALUES (?, ?, ?)";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, internId);
            ps.setString(2, noteContent);
            ps.setDate(3, new java.sql.Date(createdAt.getTime()));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return 0;
    }

    public void deleteNote(int internId, String noteContent, Date createdAt) {
        String query = "DELETE FROM [dbo].[Notes] WHERE [intern_id] = ? AND [note] = ? AND [created_at] = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, internId);
            ps.setString(2, noteContent);
            ps.setDate(3, new java.sql.Date(createdAt.getTime()));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public List<Attendance> getAttendance(int internId) {
        List<Attendance> list = new ArrayList<>();
        String query = "SELECT * FROM Attendance WHERE intern_id = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, internId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Attendance a = new Attendance(
                        rs.getInt("attendanceId"),
                        rs.getInt("internId"),
                        rs.getString("userId"),
                        rs.getDate("date"),
                        rs.getString("status")
                );
                list.add(a);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {

        }
    }

    public void insertAttendance(int internId, Date date, String status) {
        String query = "INSERT INTO [dbo].[Attendance] ([intern_id], [date], [status]) VALUES (?, ?, ?)";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, internId);
            ps.setDate(2, (java.sql.Date) date);
            ps.setString(3, status);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public List<MemberWithPosition> getListMemberProject(String projectCode) {
        List<MemberWithPosition> memberList = new ArrayList<>();
        String query = "SELECT u.user_id, u.username, u.full_name, po.position_code, po.position_name\n"
                + "FROM Account u \n"
                + "JOIN Interns i ON u.user_id = i.user_id \n"
                + "JOIN Projects p ON p.project_code = i.project_code\n"
                + "JOIN Positions po ON po.position_code = i.position_code\n"
                + "WHERE i.project_code LIKE ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
            rs = ps.executeQuery();
            while (rs.next()) {
                MemberWithPosition member = new MemberWithPosition();
                member.setUser_id(rs.getString("user_id"));
                member.setUsername(rs.getString("username"));
                member.setFull_name(rs.getString("full_name"));
                member.setPosition_name(rs.getString("position_name"));
                memberList.add(member);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return memberList;
    }

    public List<String> getListProjectCodesByUserId(String user_id) {
        List<String> projectCodes = new ArrayList<>();
        String query = "SELECT i.project_code FROM Interns i\n"
                + "JOIN Account a ON a.user_id = i.user_id\n"
                + "WHERE a.user_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                projectCodes.add(rs.getString("project_code"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return projectCodes;
    }

    public List<Messages> getListMessages(int internId) {
        List<Messages> list = new ArrayList<>();
        String query = "SELECT m.message_id, m.sender_id, m.receiver_id, m.message, m.timestamp "
                + "FROM Messages m "
                + "JOIN Account a ON a.user_id = m.sender_id "
                + "JOIN Interns i ON i.user_id = a.user_id "
                + "WHERE i.intern_id = ?;";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, internId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Messages m = new Messages(
                            rs.getInt("message_id"),
                            rs.getString("sender_id"),
                            rs.getString("receiver_id"),
                            rs.getString("message"),
                            rs.getTimestamp("timestamp")
                    );
                    list.add(m);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Messages> getMessagesByReceiverId(int internId, String receiverId) {
        List<Messages> list = new ArrayList<>();
        String query = "SELECT m.message_id, m.sender_id, m.receiver_id, m.message, m.timestamp "
                + "FROM Messages m "
                + "JOIN Account a ON a.user_id = m.sender_id "
                + "JOIN Interns i ON i.user_id = a.user_id "
                + "WHERE i.intern_id = ? AND m.receiver_id = ?;";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, internId);
            ps.setString(2, receiverId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Messages m = new Messages(
                            rs.getInt("message_id"),
                            rs.getString("sender_id"),
                            rs.getString("receiver_id"),
                            rs.getString("message"),
                            rs.getTimestamp("timestamp")
                    );
                    list.add(m);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int sentMessages(int messageId, String senderId, String receiverId, String message, Timestamp timestamp) {
        String query = "INSERT INTO [dbo].[Messages] "
                + "(sender_id, receiver_id, message, timestamp) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, senderId);
            ps.setString(2, receiverId);
            ps.setString(3, message);
            ps.setTimestamp(4, timestamp);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<String> getInbox(int internId) {
        List<String> list = new ArrayList<>();
        String query = "SELECT DISTINCT m.receiver_id "
                + "FROM Messages m "
                + "JOIN Account a ON a.user_id = m.sender_id "
                + "JOIN Interns i ON i.user_id = a.user_id "
                + "WHERE i.intern_id = ?;";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, internId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString("receiver_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

//    public void deleteNote(int internId, String noteContent, Date createdAt) {
//        String query = "DELETE FROM [dbo].[Notes] WHERE [intern_id] = ? AND [note] = ? AND [created_at] = ?";
//
//        try {
//            conn = new DBContext().getConnection();
//            ps = conn.prepareStatement(query);
//            ps.setInt(1, internId);
//            ps.setString(2, noteContent);
//            ps.setDate(3, new java.sql.Date(createdAt.getTime()));
//            ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
    public List<models.Certificate> getListCertificate(int internId) {
        List<models.Certificate> list = new ArrayList<>();
        String query = "SELECT * FROM Certificate WHERE intern_id = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, internId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    models.Certificate c = new models.Certificate(
                            rs.getInt("cer_id"),
                            rs.getString("cer_name"),
                            rs.getDate("issue_date"),
                            rs.getString("project_code"),
                            rs.getString("cer_img"),
                            rs.getString("cer_link"),
                            rs.getInt("intern_id"),
                            rs.getString("sender_id")
                    );
                    list.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Reports> getListReports(int internId) {
        List<Reports> list = new ArrayList<>();
        String query = "SELECT * FROM Reports WHERE intern_id = ? ORDER BY report_id DESC";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, internId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reports r = new Reports(
                            rs.getInt("report_id"),
                            rs.getInt("intern_id"),
                            rs.getInt("week"),
                            rs.getString("report"),
                            rs.getString("report_link"),
                            rs.getString("mentor_id"),
                            rs.getString("project_code")
                    );
                    list.add(r);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int createReport(int internId, int week, String report, String reportLink, String mentorId, String projectCode) {
        String query = "INSERT INTO Reports (intern_id, week, report, report_link, mentor_id, project_code) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, internId);
            ps.setInt(2, week);
            ps.setString(3, report);
            ps.setString(4, reportLink);
            ps.setString(5, mentorId);
            ps.setString(6, projectCode);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int editReport(int reportId, String report, String reportLink, String mentorId, String projectCode) {
        String query = "UPDATE Reports SET report = ?, report_link = ?, mentor_id = ?, project_code = ? WHERE report_id=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, report);
            ps.setString(2, reportLink);
            ps.setString(3, mentorId);
            ps.setString(4, projectCode);
            ps.setInt(5, reportId);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getMentoridbyProjectcode(String projectCode) {

        String query = "SELECT mentor_id from interns WHERE project_code = ?";
        String mentorId = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
            rs = ps.executeQuery();

            while (rs.next()) {
                mentorId = rs.getString("mentor_id");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mentorId;
    }

    public Reports getReportById(int reportId) {
        String query = "SELECT * FROM Reports WHERE report_id = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, reportId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Reports(
                            rs.getInt("report_id"),
                            rs.getInt("intern_id"),
                            rs.getInt("week"),
                            rs.getString("report"),
                            rs.getString("report_link"),
                            rs.getString("mentor_id"),
                            rs.getString("project_code")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Notifications> getAllNotificationByIntern(String userId) {
        List<Notifications> list = new ArrayList<>();
        String query = "SELECT *\n"
                + "from Notifications n\n"
                + "join Interns i on i.mentor_id = n.sender_id\n"
                + "where i.user_id LIKE ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Notifications(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getTime(7),
                        rs.getDate(8),
                        rs.getDate(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)));
            }
        } catch (Exception e) {

        }
        return list;

    }

    public Notifications getNotificationById(int notificationId) {
        String query = "SELECT * \n"
                + "FROM Notifications \n"
                + "WHERE notification_id = ?";
        Notifications notification = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, notificationId);
            rs = ps.executeQuery();
            while (rs.next()) {
                notification = new Notifications(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getTime(7),
                        rs.getDate(8),
                        rs.getDate(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notification;
    }

    public static void main(String[] args) {
        InternDao dao = new InternDao();

        List<models.Certificate> internId = dao.getListCertificate(7);
        System.out.println(internId);
    }
}
