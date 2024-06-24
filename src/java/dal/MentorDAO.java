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
import models.Account;
import models.Interns;
import models.Notifications;
import models.Project;



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
        String query = "SELECT i.intern_id, a.full_name, i.project_code, i.position_code, r.week, r.report, r.report_link " +
                       "FROM Interns i " +
                       "INNER JOIN Account a ON i.user_id = a.user_id " +
                       "INNER JOIN Reports r ON i.intern_id = r.intern_id";
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
        String query = "SELECT i.intern_id, a.full_name, i.project_code, i.position_code, r.week, r.report, r.report_link " +
                       "FROM Interns i " +
                       "INNER JOIN Account a ON i.user_id = a.user_id " +
                       "INNER JOIN Reports r ON i.intern_id = r.intern_id " +
                       "WHERE i.project_code = ?";
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
        String query = "SELECT * FROM Certificate WHERE sender_id = ?";
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
                project.setProjectName(rs.getString("project_code"));
                project.setProjectCode(rs.getString("project_code"));
                projectsList.add(project);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectsList;
    }

        public List<Interns> getInternIdbyProject (String projectCode) {
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

    public List<Account> getAllCandidateByProjectCode(String projectCode) {
        List<Account> list = new ArrayList<>();
        String query = "SELECT *\n"
                + "FROM Account ac \n"
                + "join Applications ap ON ac.user_id = ap.applicant_id\n"
                + "WHERE ap.project_code LIKE ?";
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
    
  /*public static void main(String[] args) {
    MentorDAO dao = new MentorDAO();
    String userId = "ME001"; // Thay bằng mentor_id mà bạn muốn kiểm tra
    List<String> list = dao.getProjectNamesByUserId(userId);
    
    if (list.isEmpty()) {
        System.out.println("No projects found for mentor_id: " + userId);
    } else {
        System.out.println("Projects for mentor_id: " + userId);
        for (String projectName : list) {
            System.out.println(projectName);
        }
    }
}*/
    /*public static void main(String[] args) {
        // Replace "your_mentor_id_here" with an actual mentor ID from your database
        String mentorId = "ME001";

        MentorDAO dao = new MentorDAO();

        // Call the DAO method to fetch reports for the mentor ID
        List<Reports> reportsList = dao.getAlReportsByMentorID(mentorId);
        // Print the fetched reports
        for (Reports report : reportsList) {
            
            System.out.println("Intern ID: " + report.getInternId());
            System.out.println("Week: " + report.getWeek());
            System.out.println("Report: " + report.getReport());
            System.out.println("Report Link: " + report.getReportLink());
            System.out.println("Project Code: " + report.getProjectCode());
            
            System.out.println("-----------------------");
        }
    }*/
}
    /*public static void main(String[] args) {
        MentorDAO projectDAO = new MentorDAO();
        String projectName = "PRJ007"; // Thay bằng tên dự án mà bạn muốn kiểm tra
        
        List<ReportsMentor> reports = projectDAO.getReportsByProjectName(projectName);
        
        if (reports.isEmpty()) {
            System.out.println("No reports found for project: " + projectName);
        } else {
            System.out.println("Reports for project: " + projectName);
            for (ReportsMentor report : reports) {
                System.out.println(report);
            }
        }
    }*/


