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
import models.Certificate;

import models.Positions;
import models.Projects;
import java.sql.Time;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import models.Account;
import models.CandidateApply;
import models.InternSchedule;
import models.InternWithInternSchedule;
import models.Interns;
import models.InterviewSchedule;
import models.MentorDetail;
import models.Messages;
import models.Notifications;
import models.Project;
import models.ProjectWithPositions;

import models.ReportsMentor;

/**
 *
 * @author ADM
 */
public class MentorDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // Method lấy project name theo userID ở thanh select
    public List<Project> getProjectsByUserId(String userId) {
        String query = "SELECT project_code, project_name FROM Projects WHERE mentor_id = ?";
        List<Project> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Project(rs.getString("project_code"),
                        rs.getString("project_name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Method get tất cả báo cáo 
    public List<ReportsMentor> getAllReports() {
        String query = "SELECT i.intern_id, a.full_name, i.project_code, i.position_code, r.week, r.report, r.report_link "
                + "FROM Interns i "
                + "INNER JOIN Account a ON i.user_id = a.user_id "
                + "INNER JOIN Reports r ON i.intern_id = r.intern_id";
        List<ReportsMentor> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ReportsMentor(
                        rs.getInt("intern_id"),
                        rs.getString("full_name"),
                        rs.getString("project_code"),
                        rs.getString("position_code"),
                        rs.getInt("week"),
                        rs.getString("report"),
                        rs.getString("report_link")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Method to SELECT  reports by project name
    public List<ReportsMentor> getReportsByProjectName(String projectName) {
        String query = "SELECT i.intern_id, a.full_name, i.project_code, i.position_code, r.week, r.report, r.report_link "
                + "FROM Interns i "
                + "INNER JOIN Account a ON i.user_id = a.user_id "
                + "INNER JOIN Reports r ON i.intern_id = r.intern_id "
                + "WHERE i.project_code = ?";
        List<ReportsMentor> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectName);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ReportsMentor(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // chiến 
    public List<Certificate> getListCertificate(String userId) {
        List<Certificate> list = new ArrayList<>();
        String query = "SELECT * FROM Certificate WHERE sender_id = ? order by cer_id DESC";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Certificate c = new Certificate(
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

    public int addCertificate(String cerName, Date issueDate, String projectCode, String cerImg, String cerLink, int internId, String senderId) {
        String query = "INSERT INTO Certificate (cer_name, issue_date, project_code, cer_img, cer_link, intern_id, sender_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, cerName);
            ps.setDate(2, (java.sql.Date) issueDate);
            ps.setString(3, projectCode);
            ps.setString(4, cerImg);
            ps.setString(5, cerLink);
            ps.setInt(6, internId);
            ps.setString(7, senderId);

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Certificate getCertificateById(int cerId) {
        String query = "SELECT * FROM Certificate WHERE cer_id = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, cerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Certificate(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getDate(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getInt(7),
                            rs.getString(8)
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int editCertificate(int cerId, String cerName, Date issueDate, String projectCode, String cerImg, String cerLink, int internId, String senderId) {
        String query = "UPDATE Certificate SET cer_name = ?, issue_date = ?, project_code = ?, cer_img = ?, cer_link = ?, intern_id = ?, sender_id = ? WHERE cer_id = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, cerName);
            ps.setDate(2, (java.sql.Date) issueDate);
            ps.setString(3, projectCode);
            ps.setString(4, cerImg);
            ps.setString(5, cerLink);
            ps.setInt(6, internId);
            ps.setString(7, senderId);
            ps.setInt(8, cerId);

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Projects> getProjectCodebyMentor(String user_id) {
        String query = "SELECT p.project_img, p.project_name, p.project_code FROM Projects"
                + " p JOIN Account a ON a.user_id = p.mentor_id"
                + " WHERE a.user_id =?";
        List<Projects> projectsList = new ArrayList<>();

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Projects project = new Projects();
                project.setProjectImg(rs.getString("project_Img"));
                project.setProjectName(rs.getString("project_name"));
                project.setProjectCode(rs.getString("project_code"));
                projectsList.add(project);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectsList;
    }

    public List<Interns> getInternIdbyProject(String projectCode) {
        List<Interns> list = new ArrayList<>();
        String query = "SELECT  *\n"
                + "                FROM Interns i\n"
                + "                JOIN Projects p ON p.project_code = i.project_code\n"
                + "                WHERE p.project_code LIKE ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Interns(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));

            }
        } catch (Exception e) {
        }
        return list;
    }

    // đông 
    //lay danh sach project
    public List<Projects> getALLProjectByMentor(String mentor_id) {
        List<Projects> list = new ArrayList<>();
        String query = "SELECT * FROM Projects p\n"
                + "WHERE p.mentor_id LIKE ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, mentor_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Projects(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }
//iter2
    //lay danh sach position

    public List<Positions> getAllPositionByProjectCode(String projectCode) {
        List<Positions> list = new ArrayList<>();
        String query = "SELECT  pos.position_code, pos.position_name, pos.position_count\n"
                + "FROM Positions pos\n"
                + "JOIN Projects p ON p.project_code = pos.project_code\n"
                + "WHERE p.project_code LIKE ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Positions(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    //ham add notification

    public void addNotification(String send_id, String project_code, String position_code, String message, String title, Time time, Date date_start, Date published_date, String room, String link) {
        String query = "INSERT INTO [dbo].[Notifications]\n"
                + "           ([sender_id]\n"
                + "           ,[project_code]\n"
                + "           ,[position_code]\n"
                + "           ,[message]\n"
                + "           ,[title]\n"
                + "           ,[Time]\n"
                + "           ,[date_start]\n"
                + "           ,[published_date]\n"
                + "           ,[room]\n"
                + "           ,[link])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?)";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, send_id);
            ps.setString(2, project_code);
            ps.setString(3, position_code);
            ps.setString(4, message);
            ps.setString(5, title);
            ps.setTime(6, time);
            ps.setDate(7, new java.sql.Date(date_start.getTime()));
            ps.setDate(8, new java.sql.Date(published_date.getTime()));
            ps.setString(9, room);
            ps.setString(10, link);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //view list notification
    public List<Notifications> getAllNotificationByMentor(String projectCode) {
        List<Notifications> list = new ArrayList<>();
        String query = "SELECT * FROM Notifications Where project_code LIKE ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
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
            e.printStackTrace(); // In lỗi ra console để kiểm tra
        }
        return list;
    }

    //view lich phong van by mentor
    public List<InterviewSchedule> getAllInterviewScheduleByMentorId(String mentorId) {
        List<InterviewSchedule> list = new ArrayList<>();
        String query = "select * from InterviewSchedule n\n"
                + "where n.mentor_id LIKE ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, mentorId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new InterviewSchedule(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getTime(7),
                        rs.getDate(8),
                        rs.getString(9)));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<CandidateApply> getAllCandidateByProjectCode(String projectCode) {
        List<CandidateApply> list = new ArrayList<>();
        String query = "select u.username, a.project_code,a.position_code,a.status, u.user_id, u.full_name,u.dob,u.gender,u.phone_number,u.specialization\n"
                + "from Account u\n"
                + "join Applications a on a.applicant_id = u.user_id\n"
                + " Where a.project_code LIKE ? and a.status = 'Pending'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new CandidateApply(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)));
            }

        } catch (Exception e) {

        }
        return list;
    }

    public List<Account> getAllInternByProjectCode(String projectCode) {
        List<Account> list = new ArrayList<>();
        String query = "SELECT *\n"
                + "FROM Account ac \n"
                + "join Interns i ON ac.user_id = i.user_id\n"
                + "WHERE i.project_code LIKE ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getByte(11)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ProjectWithPositions getProjectByProjectCode(String projectCode) {
        String query = "SELECT p.project_code, p.project_name, p.mentor_id, p.project_img, p.project_details, p.project_startday, p.project_endday,\n"
                + "                pos.position_code, pos.position_name, pos.position_count \n"
                + "                FROM Projects p \n"
                + "                LEFT JOIN Positions pos ON p.project_code = pos.project_code \n"
                + "                WHERE p.project_code LIKE ? \n"
                + "                ORDER BY p.project_code, pos.position_code;";
        ProjectWithPositions project = null;
        List<Positions> positionsList = new ArrayList<>();

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
            rs = ps.executeQuery();

            while (rs.next()) {
                if (project == null) {
                    project = new ProjectWithPositions();
                    project.setProjectCode(rs.getString("project_code"));
                    project.setProjectName(rs.getString("project_name"));
                    project.setMentorId(rs.getString("mentor_id"));
                    project.setDescription(rs.getString("project_details"));
                    project.setProjectImage(rs.getString("project_img"));
                    project.setProjectStartDay(rs.getDate("project_startday"));
                    project.setProjectEndDay(rs.getDate("project_endday"));
                    project.setPositions(positionsList); // set the positions list
                }

                String positionCode = rs.getString("position_code");
                if (positionCode != null) {
                    Positions position = new Positions();
                    position.setPositionCode(positionCode);
                    position.setPositionName(rs.getString("position_name"));
                    position.setPositionCount(rs.getInt("position_count"));
                    positionsList.add(position);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return project;
    }

    public void deleteNotificationById(int notificattionid) {
        String query = "DELETE FROM [dbo].[Notifications]\n"
                + "      WHERE notification_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, notificattionid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean certificateExists(int internId, String projectCode) {
        String query = "SELECT COUNT(*) FROM Certificate WHERE intern_id = ? and project_code like ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, internId);
            ps.setString(2, projectCode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public Notifications getNotificationById(int notificationId) {
        String query = "SELECT *\n"
                + "FROM Notifications\n"
                + "WHERE notification_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, notificationId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Notifications(rs.getInt(1),
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
        return null;
    }

    public int updateNotification(int notificationId, String send_id, String project_code, String position_code, String message, String title, Time time, Date date_start, Date published_date, String room, String link) {
        String query = "UPDATE [dbo].[Notifications]\n"
                + "   SET [sender_id] = ?\n"
                + "      ,[project_code] = ?\n"
                + "      ,[position_code] =?\n"
                + "      ,[message] =?\n"
                + "      ,[title] = ?\n"
                + "      ,[Time] = ?\n"
                + "      ,[date_start] = ?\n"
                + "      ,[published_date] = ?\n"
                + "      ,[room] = ?\n"
                + "      ,[link] = ?\n"
                + " WHERE [notification_id] = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, send_id);
            ps.setString(2, project_code);
            ps.setString(3, position_code);
            ps.setString(4, message);
            ps.setString(5, title);
            ps.setTime(6, time);
            ps.setDate(7, new java.sql.Date(date_start.getTime()));
            ps.setDate(8, new java.sql.Date(published_date.getTime()));
            ps.setString(9, room);
            ps.setString(10, link);
            ps.setInt(11, notificationId);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return 0;
    }

    public List<Messages> getMessagesForMentor(String user_id) {
        List<Messages> messages = new ArrayList<>();
        String query = "SELECT m.message_id, m.sender_id, m.receiver_id, m.message, m.timestamp, m.subject\n"
                + "FROM Messages m\n"
                + "JOIN Account a ON a.user_id = m.sender_id OR a.user_id = m.receiver_id\n"
                + "WHERE a.user_id = ?\n"
                + "AND NOT EXISTS (\n"
                + "    SELECT 1 FROM Messages m2\n"
                + "    WHERE (m2.sender_id = m.sender_id AND m2.receiver_id = m.receiver_id\n"
                + "        OR m2.sender_id = m.receiver_id AND m2.receiver_id = m.sender_id)\n"
                + "    AND m2.subject = m.subject\n"
                + "    AND m2.timestamp > m.timestamp\n"
                + ")\n"
                + "ORDER BY m.timestamp;";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user_id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Messages message = new Messages();
                    message.setMessageId(rs.getInt("message_id"));
                    message.setSenderId(rs.getString("sender_id"));
                    message.setReceiverId(rs.getString("receiver_id"));
                    message.setMessage(rs.getString("message"));
                    message.setTimestamp(rs.getTimestamp("timestamp"));
                    message.setSubject(rs.getString("subject"));
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

    public List<Messages> getRepliesByMessageId(String subject) {
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

    public int sendMessage(Messages message) {
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

    public List<Projects> getProjectsByUserId2(String userId) {
        String query = "SELECT * FROM Projects WHERE mentor_id = ?";
        List<Projects> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Projects(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Interns> getAllInternsByProject(String project_code) {
        String query = "SELECT * FROM Interns where project_code = ?;";
        List<Interns> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, project_code);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Interns(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        } catch (Exception e) {

        }

        return list;
    }

    public List<InternWithInternSchedule> getInternsWithScheduleByProject(String projectCode) {
        List<InternWithInternSchedule> list = new ArrayList<>();
        String query = "SELECT a.*, s.start_date, s.end_date, i.intern_id,i.project_code, i.position_code \n"
                + "FROM Account a \n"
                + "JOIN InternSchedule s ON a.user_id = s.user_id \n"
                + "JOIN Interns i ON a.user_id = i.user_id \n"
                + "WHERE a.role_id = 5 AND i.project_code = ? \n"
                + "ORDER BY s.schedule_id DESC";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new InternWithInternSchedule(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getByte(11),
                        rs.getDate(13),
                        rs.getDate(14),
                        rs.getInt(15),
                        rs.getString(16),
                        rs.getString(17)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<InternWithInternSchedule> getInternsWithSchedule() {
        List<InternWithInternSchedule> list = new ArrayList<>();
        String query = "SELECT a.*, s.start_date, s.end_date, i.intern_id,i.project_code, i.position_code \n"
                + "FROM Account a JOIN InternSchedule s ON a.user_id = s.user_id JOIN Interns i ON a.user_id = i.user_id WHERE a.role_id = 5\n"
                + "ORDER BY s.schedule_id DESC";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new InternWithInternSchedule(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getByte(11),
                        rs.getDate(13),
                        rs.getDate(14),
                        rs.getInt(15),
                        rs.getString(16),
                        rs.getString(17)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public InternSchedule getInternSchedule(String userId) {
        InternSchedule schedule = null;
        String query = "SELECT start_date, end_date FROM InternSchedule WHERE user_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                schedule = new InternSchedule();
                schedule.setStartDate(rs.getDate("start_date").toLocalDate());
                schedule.setEndDate(rs.getDate("end_date").toLocalDate());
            }
        } catch (Exception e) {
        }
        return schedule;
    }

    public Map<LocalDate, String> getAttendanceRecords(String userId) {
        Map<LocalDate, String> attendanceRecords = new HashMap<>();
        String query = "SELECT date, status FROM attendance WHERE intern_id = (SELECT intern_id FROM interns WHERE user_id = ?)";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                LocalDate date = rs.getDate("date").toLocalDate();
                String status = rs.getString("status");
                attendanceRecords.put(date, status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return attendanceRecords;
    }

    public int countPresentDays(String userId) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM attendance WHERE intern_id = (SELECT intern_id FROM interns WHERE user_id = ?) AND status = 'present'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
        }

        return count;
    }

    public Map<String, Integer> getPresentDaysByUserId() {
        Map<String, Integer> presentDaysMap = new HashMap<>();
        String query = "SELECT i.user_id, COUNT(a.status) AS present_days "
                + "FROM interns i "
                + "LEFT JOIN attendance a ON i.intern_id = a.intern_id AND a.status = 'present' "
                + "GROUP BY i.user_id";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("user_id");
                int presentDays = rs.getInt("present_days");
                presentDaysMap.put(userId, presentDays);
            }
        } catch (Exception e) {
        }

        return presentDaysMap;
    }

    public Map<String, String> getAllPositions() {
        Map<String, String> positions = new HashMap<>();
        String query = "SELECT position_code, position_name FROM positions";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String positionCode = rs.getString("position_code");
                String positionName = rs.getString("position_name");
                positions.put(positionCode, positionName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return positions;
    }

    public List<MentorDetail> getDetailByMentorId(String mentorId) {
        List<MentorDetail> list = new ArrayList<>();
        String query = "SELECT DISTINCT a.user_id, a.full_name, a.avatar, a.username,a.gender\n"
                + "                       FROM Account a \n"
                + "                       JOIN Projects p ON a.user_id = p.mentor_id \n"
                + "                       WHERE p.mentor_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, mentorId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new MentorDetail(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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

    public List<CandidateApply> getAllCandidateByProjectCode2(String projectCode) {
        List<CandidateApply> list = new ArrayList<>();
        String query = "select u.username, a.project_code,a.position_code,a.status, u.user_id, u.full_name,u.dob,u.gender,u.phone_number,u.specialization\n"
                + "from Account u\n"
                + "join Applications a on a.applicant_id = u.user_id\n"
                + " Where a.project_code LIKE ? and a.status = 'Pending'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new CandidateApply(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)));
            }

        } catch (Exception e) {

        }
        return list;
    }

    // Method get tất cả báo cáo 
    public List<ReportsMentor> getAllReports(String userId) {
//        String query = "SELECT i.intern_id, a.full_name, i.project_code, i.position_code, r.week, r.report, r.report_link "
//                + "FROM Interns i "
//                + "INNER JOIN Account a ON i.user_id = a.user_id "
//                + "INNER JOIN Reports r ON i.intern_id = r.intern_id";
        String query = "SELECT i.intern_id, a.full_name, i.project_code, i.position_code, r.week, r.report, r.report_link \n"
                + "FROM Interns i \n"
                + "INNER JOIN Account a ON i.user_id = a.user_id \n"
                + "INNER JOIN Reports r ON i.intern_id = r.intern_id \n"
                + "WHERE i.mentor_id =? ";
        List<ReportsMentor> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ReportsMentor(
                        rs.getInt("intern_id"),
                        rs.getString("full_name"),
                        rs.getString("project_code"),
                        rs.getString("position_code"),
                        rs.getInt("week"),
                        rs.getString("report"),
                        rs.getString("report_link")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<CandidateApply> getAllCandidateByProjectCode1(String projectCode) {
        List<CandidateApply> list = new ArrayList<>();
        String query = "select u.username, a.project_code,a.position_code,a.status, u.user_id, u.full_name,u.dob,u.gender,u.phone_number,u.specialization\n"
                + "from Account u\n"
                + "join Applications a on a.applicant_id = u.user_id\n"
                + " Where a.project_code LIKE ? and a.status = 'Pending'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new CandidateApply(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)));
            }

        } catch (Exception e) {

        }
        return list;
    }

    
}
