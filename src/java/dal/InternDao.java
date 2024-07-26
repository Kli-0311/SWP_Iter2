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
import models.AttendanceList;

import models.Interns;
import models.MemberWithPosition;

import models.Messages;
import models.Notes;
import models.Notifications;
import models.ProjectAttendanceByIntern;
import models.Projects;
import models.Reports;
import models.ViewGradeByIntern;
import models.ViewGradeByNameIntern;

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

    public boolean hasAlreadyCheckedIn(int internId, Date date) {
        String query = "SELECT COUNT(*) FROM [dbo].[Attendance] WHERE intern_id = ? AND date = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, internId);
            ps.setDate(2, new java.sql.Date(date.getTime()));
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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

    public boolean reportExists(int week, String projectCode) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM reports WHERE week = ? AND project_code = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, week);
            ps.setString(2, projectCode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    exists = rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    public List<Messages> getListQuestion(int internId) {
        List<Messages> list = new ArrayList<>();
        String query = "SELECT m.message_id, m.sender_id, m.receiver_id, m.message, m.timestamp, m.subject\n"
                + "FROM Messages m\n"
                + "JOIN Account a ON a.user_id = m.sender_id OR a.user_id = m.receiver_id\n"
                + "JOIN Interns i ON i.user_id = a.user_id\n"
                + "WHERE i.intern_id = ?\n"
                + "AND NOT EXISTS (\n"
                + "    SELECT 1 FROM Messages m2\n"
                + "    WHERE (m2.sender_id = m.sender_id AND m2.receiver_id = m.receiver_id\n"
                + "        OR m2.sender_id = m.receiver_id AND m2.receiver_id = m.sender_id)\n"
                + "    AND m2.subject = m.subject\n"
                + "    AND m2.timestamp > m.timestamp\n"
                + ")\n"
                + "ORDER BY m.timestamp";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, internId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Messages m = new Messages(
                            rs.getInt("message_id"),
                            rs.getString("sender_id"),
                            rs.getString("receiver_id"),
                            rs.getString("message"),
                            rs.getTimestamp("timestamp"),
                            rs.getString("subject")
                    );
                    list.add(m);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

   
    public int sentQuestion(Messages message) {
        String query = "INSERT INTO [dbo].[Messages] "
                + "(sender_id, receiver_id, message, timestamp, subject) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, message.getSenderId());
            ps.setString(2, message.getReceiverId());
            ps.setString(3, message.getMessage());
            ps.setTimestamp(4, message.getTimestamp());
            ps.setString(5, message.getSubject());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return 0;
    }

    public Date getInternStartDate(String userId) {
        String query = "SELECT start_date FROM [InternSchedule] WHERE user_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDate("start_date");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Date getInternEndDate(String userId) {
        String query = "SELECT end_date FROM [InternSchedule] WHERE user_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDate("end_date");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getInternName(String userId) {
        String internName = "";
        String query = "SELECT full_name FROM Account WHERE user_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                internName = rs.getString("full_name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return internName;
    }

    public List<Attendance> getAttendanceRecords(int internId) {
        List<Attendance> records = new ArrayList<>();
        String query = "SELECT date, status FROM attendance WHERE intern_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, internId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Attendance record = new Attendance();
                record.setDate(rs.getDate("date"));
                record.setStatus(rs.getString("status"));
                records.add(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }

    //inter 3 : HUYỀN 
    //view list attendance 
    public List<AttendanceList> getAttendanceByIntern(String userId) {
        List<AttendanceList> list = new ArrayList<>();
        String query = "SELECT \n"
                + "    ROW_NUMBER() OVER (ORDER BY a.[date]) as [No], \n"
                + "    a.[attendance_id], \n"
                + "    a.[intern_id], \n"
                + "    i.[user_id], \n"
                + "    a.[date], \n"
                + "    a.[status]\n"
                + "FROM \n"
                + "    [dbo].[Attendance] a\n"
                + "JOIN \n"
                + "    [dbo].[Interns] i ON a.[intern_id] = i.[intern_id]\n"
                + "WHERE \n"
                + "    i.[user_id] = ? ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new AttendanceList(
                        rs.getInt(1), // No
                        rs.getInt(2), // attendance_id
                        rs.getInt(3), // intern_id
                        rs.getString(4), // user_id
                        rs.getDate(5), // date
                        rs.getString(6) // status
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }
        return list;
    }

    // view project name , mentor name của inter đó 
    public List<ProjectAttendanceByIntern> getProjectInfoByUserID(String userId) {
        List<ProjectAttendanceByIntern> list = new ArrayList<>();
        String query = "SELECT i.intern_id,\n"
                + "       a.full_name AS mentor_name,\n"
                + "       p.project_name\n"
                + "FROM [dbo].[Interns] i\n"
                + "INNER JOIN [dbo].[Account] a ON i.mentor_id = a.user_id\n"
                + "INNER JOIN [dbo].[Projects] p ON i.project_code = p.project_code\n"
                + "WHERE i.user_id = ? ";

        try {

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProjectAttendanceByIntern(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                ));
            }

        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }
        return list;
    }

    //phân trang 
    // hàm đếm xem bảng attendance có bao nhiêu 
    public int getTotalAttendance(String userId) {
        String query = "SELECT COUNT(*) FROM [dbo].[Attendance] a \n"
                + "                 JOIN [dbo].[Interns] i ON a.[intern_id] = i.[intern_id] \n"
                + "                 WHERE i.[user_id] = ? ";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);// trong sql nó chỉ trả về 1 kq
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // indext là cái số trang mik nhấn vào 
    public List<AttendanceList> pagingNews(int index, String userId) {
        List<AttendanceList> list = new ArrayList<>();
        String query = "SELECT ROW_NUMBER() OVER (ORDER BY a.[date]) as [No], a.[attendance_id], a.[intern_id], i.[user_id], a.[date], a.[status]\n"
                + "FROM  [dbo].[Attendance] a\n"
                + "JOIN  [dbo].[Interns] i ON a.[intern_id] = i.[intern_id]\n"
                + "WHERE i.[user_id] = ? \n"
                + "ORDER BY a.[date]\n"
                + "OFFSET ? ROWS FETCH NEXT 5  ROWS ONLY ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            ps.setInt(2, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new AttendanceList(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6)
                ));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<String> getListMentor(String user_id) {
        String query = "SELECT mentor_id FROM Interns WHERE user_id = ?";
        List<String> mentorIds = new ArrayList<>();

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String mentorId = rs.getString("mentor_id");
                if (mentorId != null && !mentorId.isEmpty()) {
                    mentorIds.add(mentorId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mentorIds;
    }

    public List<Messages> SearchBySubject(String subject) {
        List<Messages> messages = new ArrayList<>();
        String query = "SELECT message_id, sender_id, receiver_id, message, timestamp, subject "
                + "FROM Messages WHERE subject LIKE ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + subject + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Messages message = new Messages(
                            rs.getInt("message_id"),
                            rs.getString("sender_id"),
                            rs.getString("receiver_id"),
                            rs.getString("message"),
                            rs.getTimestamp("timestamp"),
                            rs.getString("subject")
                    );
                    messages.add(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }

    public Messages getMessageById(int messageId) {
        Messages message = null;
        String query = "SELECT message_id, sender_id, receiver_id, message, timestamp, subject FROM Messages WHERE message_id = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, messageId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    message = new Messages(
                            rs.getInt("message_id"),
                            rs.getString("sender_id"),
                            rs.getString("receiver_id"),
                            rs.getString("message"),
                            rs.getTimestamp("timestamp"),
                            rs.getString("subject")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public List<Messages> getSubjectDetail(String subject) {
        List<Messages> list = new ArrayList<>();
        String query = "SELECT * FROM Messages WHERE subject = ? ORDER BY timestamp";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, subject);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Messages m = new Messages(
                            rs.getInt("message_id"),
                            rs.getString("sender_id"),
                            rs.getString("receiver_id"),
                            rs.getString("message"),
                            rs.getTimestamp("timestamp"),
                            rs.getString("subject")
                    );
                    list.add(m);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public int sendQuestion(Messages message) {
        String query = "INSERT INTO [dbo].[Messages] "
                + "(sender_id, receiver_id, message, timestamp, subject) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, message.getSenderId());
            ps.setString(2, message.getReceiverId());
            ps.setString(3, message.getMessage());
            ps.setTimestamp(4, message.getTimestamp());
            ps.setString(5, message.getSubject());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return 0;
    }
    
    // hUYỀN  cho intern xem điểm của minh
    public List<ViewGradeByIntern> getGradeByIntern(String userId) {
    List<ViewGradeByIntern> list = new ArrayList<>();
        String query = "SELECT i.intern_id,a.full_name ,e.type,e.attitude_score,e.soft_skills_score,e.technical_skills_score,e.total_score,e.comment,p.project_name,pos.position_name\n"
                + "FROM  [dbo].[Interns] i\n"
                + "JOIN [dbo].[Evaluations] e ON i.intern_id = e.intern_id\n"
                + "JOIN [dbo].[Projects] p ON i.project_code = p.project_code\n"
                + "JOIN [dbo].[Positions] pos ON i.position_code = pos.position_code AND i.project_code = pos.project_code\n"
                + "JOIN [dbo].[Account] a ON i.mentor_id = a.user_id\n"
                + "WHERE i.user_id = ? \n"
                + "ORDER BY i.intern_id;";
        
        
        
        try {
            
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
           while (rs.next()) {
                list.add(new ViewGradeByIntern(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                ));
            }
           
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }
        return list;
    
    }
    // HUYỀN cho name của intern 
    public List<ViewGradeByNameIntern> getNameGradeByIntern(String userId) {
        List<ViewGradeByNameIntern> name = new ArrayList<>();
        String query = "SELECT i.intern_id,a.full_name AS intern_name\n"
                + "FROM [Project1].[dbo].[Interns] i\n"
                + "JOIN [Project1].[dbo].[Account] a ON i.user_id = a.user_id\n"
                + "WHERE i.user_id = ? ;";
        
        
        
        try {
            
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
           while (rs.next()) {
                name.add(new ViewGradeByNameIntern(                       
                        rs.getString(2)
                        
                ));
            }
           
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }
        return name;
    
    }


    public static void main(String[] args) {
        
    }
}
