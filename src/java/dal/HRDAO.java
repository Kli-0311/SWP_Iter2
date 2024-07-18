/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Account;
import models.InterviewSchedule;
import models.Notifications;
import models.Positions;
import models.ProjectWithPositions;
import models.Projects;

/**
 *
 * @author admin
 */
public class HRDAO extends DBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<ProjectWithPositions> getAllProjectsWithPositions() {
        Map<String, ProjectWithPositions> projectMap = new HashMap<>();
        String query = "SELECT p.project_code, p.project_name, p.mentor_id, p.project_img, p.project_details,p.project_startday, p.project_endday,\n"
                + "                pos.position_code, pos.position_name, pos.position_count \n"
                + "                FROM Projects p \n"
                + "                LEFT JOIN Positions pos ON p.project_code = pos.project_code \n"
                + "                ORDER BY p.project_code, pos.position_code";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String projectCode = rs.getString("project_code");
                ProjectWithPositions project = projectMap.get(projectCode);
                if (project == null) {
                    project = new ProjectWithPositions();
                    project.setProjectCode(rs.getString("project_code"));
                    project.setProjectName(rs.getString("project_name"));
                    project.setMentorId(rs.getString("mentor_id"));
                    project.setProjectImage(rs.getString("project_img"));
                    project.setDescription(rs.getString("project_details"));
                    project.setProjectStartDay(rs.getDate("project_startday"));
                    project.setProjectEndDay(rs.getDate("project_endday"));
                    project.setPositions(new ArrayList<>());
                    projectMap.put(projectCode, project);
                }

                String positionCode = rs.getString("position_code");
                if (positionCode != null) {  // Kiểm tra xem positionCode có null không
                    Positions position = new Positions();
                    position.setPositionCode(rs.getString("position_code"));
                    position.setPositionName(rs.getString("position_name"));
                    position.setPositionCount(rs.getInt("position_count"));
                    project.getPositions().add(position);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(projectMap.values());
    }

    public void addProject(String projectCode, String projectName, String mentorId, String projectImage, String projectDetails, Date projectStartDay, Date projectEndDay) {
        String query = "INSERT INTO [dbo].[Projects] ([project_code]\n"
                + "           ,[project_name]\n"
                + "           ,[mentor_id]\n"
                + "           ,[project_details]\n"
                + "           ,[project_img]\n"
                + "           ,[project_startday]\n"
                + "           ,[project_endday])\n"
                + "     VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
            ps.setString(2, projectName);
            ps.setString(3, mentorId);
            ps.setString(5, "img/" + projectImage);
            ps.setString(4, projectDetails);
            ps.setDate(6, new java.sql.Date(projectStartDay.getTime()));
            ps.setDate(7, new java.sql.Date(projectEndDay.getTime()));
            rs = ps.executeQuery();
        } catch (Exception e) {

        }
    }

    public List<Account> getAllMentor() {
        List<Account> list = new ArrayList<>();
        String query = "select * from Account a\n"
                + "where a.role_id = 4";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
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

        }
        return list;
    }

    public void addProjectAndPositions(String projectCode, String projectName, String mentorId, String projectImage, String description, Date projectStartDay, Date projectEndDay, String[] positionNames, int[] positionCounts) throws Exception {

        String insertProjectQuery = "INSERT INTO Projects (project_code, project_name, mentor_id, project_img, project_details, project_startday, project_endday) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String insertPositionQuery = "INSERT INTO Positions (project_code, position_code, position_name, position_count) VALUES (?, ?, ?, ?)";

        try {
            conn = new DBContext().getConnection();
            conn.setAutoCommit(false);  // Bắt đầu giao dịch

            // Chèn vào bảng Projects
            ps = conn.prepareStatement(insertProjectQuery);
            ps.setString(1, projectCode);
            ps.setString(2, projectName);
            ps.setString(3, mentorId);
            ps.setString(4, "img/" + projectImage);
            ps.setString(5, description);
            ps.setDate(6, new java.sql.Date(projectStartDay.getTime()));
            ps.setDate(7, new java.sql.Date(projectEndDay.getTime()));
            ps.executeUpdate();

            // Chèn vào bảng Positions
            ps = conn.prepareStatement(insertPositionQuery);
            for (int i = 0; i < positionNames.length; i++) {
                ps.setString(1, projectCode);
                ps.setString(2, projectCode + "_POS" + (i + 1)); // Tự động tạo mã vị trí
                ps.setString(3, positionNames[i]);
                ps.setInt(4, positionCounts[i]);
                ps.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {

        }
    }

    public void deleteProject(String projectCode) {

        String query = "DELETE FROM Positions WHERE project_code = ?;"
                + "DELETE FROM Projects WHERE project_code = ?;";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
            ps.setString(2, projectCode);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    //kiem tra projectCode ton tai khong
    public boolean isProjectCodeExists(String projectCode) {
        String query = "SELECT COUNT(*) FROM projects WHERE project_code LIKE ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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

    public List<Projects> getAllProjectbyHR() {
        List<Projects> list = new ArrayList<>();
        String query = "select * from Projects";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
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

    public void createInterviewShedule(String send_id, String mentor_id, String project_code, Date date_start, Time time, String message, String title, String room) {
        String query = "INSERT INTO [dbo].[InterviewSchedule]\n"
                + "           ([sender_id]\n"
                + "           ,[mentor_id]\n"
                + "           ,[project_code]\n"
                + "           ,[message]\n"
                + "           ,[title]\n"
                + "           ,[Time]\n"
                + "           ,[date_start]\n"
                + "           ,[room])\n"
                + "     VALUES (?,?,?,?,?,?,?,?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, send_id);
            ps.setString(2, mentor_id);
            ps.setString(3, project_code);
            ps.setString(4, message);
            ps.setString(5, title);
            ps.setTime(6, time);
            ps.setDate(7, new java.sql.Date(date_start.getTime()));
            ps.setString(8, room);

            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteInterviewScheduleById(int interviewscheduleId) {
        String query = "DELETE FROM InterviewSchedule WHERE interviewschedule_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, interviewscheduleId);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public List<InterviewSchedule> getAllInterviewScheduleByHR(String user_id) {
        List<InterviewSchedule> list = new ArrayList<>();
        String query = "SELECT * FROM InterviewSchedule Where sender_id LIKE ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new InterviewSchedule(rs.getInt("interviewschedule_id"),
                        rs.getString("sender_id"),
                        rs.getString("mentor_id"),
                        rs.getString("project_code"),
                        rs.getString("message"),
                        rs.getString("title"),
                        rs.getTime("time"),
                        rs.getDate("date_start"),
                        rs.getString("room")));

            }
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console để kiểm tra
        }
        return list;
    }
    public boolean isInterviewScheduleExists(String projectCode) {
        String query = "SELECT COUNT(*) FROM InterviewSchedule \n"
                + "WHERE project_code LIKE ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //update interview schedule
    public InterviewSchedule getInterviewScheduleById(int interviewScheduleId) {
        String query = "SELECT *\n"
                + "FROM InterviewSchedule\n"
                + "WHERE interviewschedule_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, interviewScheduleId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new InterviewSchedule(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getTime(7),
                        rs.getDate(8),
                        rs.getString(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updateInterviewSchedule(int interviewScheduleId, String send_id, String mentor_id, String project_code, Date date_start, Time time, String message, String title, String room) {
        String query = "UPDATE [dbo].[InterviewSchedule]\n"
                + "   SET [sender_id] = ?\n"
                + "      ,[mentor_id] = ?\n"
                + "      ,[project_code] = ?\n"
                + "      ,[message] = ?\n"
                + "      ,[title] =?\n"
                + "      ,[Time] = ?\n"
                + "      ,[date_start] = ?\n"
                + "      ,[room] = ?\n"
                + " WHERE [interviewschedule_id] = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, send_id);
            ps.setString(2, mentor_id);
            ps.setString(3, project_code);
            ps.setString(4, message);
            ps.setString(5, title);
            ps.setTime(6, time);
            ps.setDate(7, new java.sql.Date(date_start.getTime()));
            ps.setString(8, room);
            ps.setInt(9, interviewScheduleId);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
