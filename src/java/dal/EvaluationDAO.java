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
import models.Account;
import models.CandidateApply;
import models.Evaluations;
import models.GradeForInterns;
import models.Intern;
import models.Projects;
import org.apache.tomcat.dbcp.dbcp2.DelegatingConnection;

/**
 *
 * @author admin
 */
public class EvaluationDAO extends DBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void insertMidtermCore(int internId, String mentor_id,
            String type, int attitude_score, int soft_skills_score,
            int technical_skills_score, String total_score,
            String comment, String projectCode, String positionCode) {
        String query = "INSERT INTO [dbo].[Evaluations]\n"
                + "           ([intern_id]\n"
                + "           ,[mentor_id]\n"
                + "           ,[type]\n"
                + "           ,[attitude_score]\n"
                + "           ,[soft_skills_score]\n"
                + "           ,[technical_skills_score]\n"
                + "           ,[total_score]\n"
                + "           ,[comment]\n"
                + "           ,[project_code]\n"
                + "           ,[position_code])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, internId);
            ps.setString(2, mentor_id);
            ps.setString(3, type);
            ps.setInt(4, attitude_score);
            ps.setInt(5, soft_skills_score);
            ps.setInt(6, technical_skills_score);
            ps.setString(7, total_score);
            ps.setString(8, comment);
            ps.setString(9, projectCode);
            ps.setString(10, positionCode);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }

    public void insertFinalCore(int internId, String mentor_id,
            String type, int attitude_score, int soft_skills_score,
            int technical_skills_score, String total_score,
            String comment, String projectCode, String positionCode) {
        String query = "INSERT INTO [dbo].[Evaluations]\n"
                + "           ([intern_id]\n"
                + "           ,[mentor_id]\n"
                + "           ,[type]\n"
                + "           ,[attitude_score]\n"
                + "           ,[soft_skills_score]\n"
                + "           ,[technical_skills_score]\n"
                + "           ,[total_score]\n"
                + "           ,[comment]\n"
                + "           ,[project_code]\n"
                + "           ,[position_code])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, internId);
            ps.setString(2, mentor_id);
            ps.setString(3, type);
            ps.setInt(4, attitude_score);
            ps.setInt(5, soft_skills_score);
            ps.setInt(6, technical_skills_score);
            ps.setString(7, total_score);
            ps.setString(8, comment);
            ps.setString(9, projectCode);
            ps.setString(10, positionCode);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }

    public Evaluations getMitermByInternId(int internId, String projectCode) {
        String query = "SELECT *\n"
                + "FROM Evaluations\n"
                + "WHERE intern_id = ? and project_code = ? and type = 'Midterm'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, internId);
            ps.setString(2, projectCode);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Evaluations(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Evaluations getFinalByInternId(int internId, String projectCode) {
        String query = "SELECT *\n"
                + "FROM Evaluations\n"
                + "WHERE intern_id = ? and project_code = ? and type = 'Final'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, internId);
            ps.setString(2, projectCode);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Evaluations(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updateCore(int attitude_score, int soft_skills_score, int technical_skills_score, String total_score, String comment, int evaluationId) {
        String query = "UPDATE [dbo].[Evaluations]\n"
                + "   SET [attitude_score] = ?\n"
                + "      ,[soft_skills_score] = ?\n"
                + "      ,[technical_skills_score] = ?\n"
                + "      ,[total_score] = ?\n"
                + "      ,[comment] = ?\n"
                + " WHERE [evaluation_id] = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, attitude_score);
            ps.setInt(2, soft_skills_score);
            ps.setInt(3, technical_skills_score);
            ps.setString(4, total_score);
            ps.setString(5, comment);
            ps.setInt(6, evaluationId);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Intern getInternByInternId(int id) {
        String query = "SELECT * \n"
                + "FROM Interns \n"
                + "WHERE intern_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Intern(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getAccountByinternId(String userId) {
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

    public List<GradeForInterns> getAllGradeForInternByType(String type) {
        List<GradeForInterns> list = new ArrayList<>();
        String query = "SELECT \n"
                + "    ROW_NUMBER() OVER (ORDER BY e.intern_id DESC) AS stt,\n"
                + "    i.user_id, i.intern_id, a.full_name, p.position_name, pr.project_name,\n"
                + "    pr.project_code, e.mentor_id, e.comment, e.type, e.attitude_score, \n"
                + "    e.soft_skills_score, e.technical_skills_score, e.total_score\n"
                + "FROM Evaluations e\n"
                + "JOIN Interns i ON i.intern_id = e.intern_id\n"
                + "JOIN Account a ON a.user_id = i.user_id \n"
                + "JOIN Positions p ON p.position_code = e.position_code\n"
                + "JOIN Projects pr ON pr.project_code = e.project_code\n"
                + "WHERE e.type = ?\n"
                + "ORDER BY stt;";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, type);
            rs = ps.executeQuery();
            while (rs.next()) {
                GradeForInterns grade = new GradeForInterns(
                        rs.getInt("stt"),
                        rs.getString("user_id"),
                        rs.getInt("intern_id"),
                        rs.getString("full_name"),
                        rs.getString("position_name"),
                        rs.getString("project_name"),
                        rs.getString("project_code"),
                        rs.getString("mentor_id"),
                        rs.getString("comment"),
                        rs.getString("type"),
                        rs.getInt("attitude_score"),
                        rs.getInt("soft_skills_score"),
                        rs.getInt("technical_skills_score"),
                        rs.getString("total_score")
                );
                list.add(grade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<GradeForInterns> getAllGradeForInternByProjectCode(String projectCode) {
        List<GradeForInterns> list = new ArrayList<>();
        String query = "SELECT \n"
                + "    ROW_NUMBER() OVER (ORDER BY e.intern_id DESC) AS stt,\n"
                + "    i.user_id,i.intern_id, a.full_name, p.position_name, pr.project_name,\n"
                + "    pr.project_code ,e.mentor_id ,e.comment, e.type, e.attitude_score, \n"
                + "    e.soft_skills_score, e.technical_skills_score,  e.total_score\n"
                + "FROM Evaluations e\n"
                + "JOIN Interns i ON i.intern_id = e.intern_id\n"
                + "JOIN Account a ON a.user_id = i.user_id \n"
                + "JOIN Positions p ON p.position_code = e.position_code\n"
                + "JOIN Projects pr ON pr.project_code = e.project_code\n"
                + "WHERE e.project_code = ?\n"
                + "ORDER BY stt;";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
            rs = ps.executeQuery();
            while (rs.next()) {
                GradeForInterns grade = new GradeForInterns(
                        rs.getInt("stt"),
                        rs.getString("user_id"),
                        rs.getInt("intern_id"),
                        rs.getString("full_name"),
                        rs.getString("position_name"),
                        rs.getString("project_name"),
                        rs.getString("project_code"),
                        rs.getString("mentor_id"),
                        rs.getString("comment"),
                        rs.getString("type"),
                        rs.getInt("attitude_score"),
                        rs.getInt("soft_skills_score"),
                        rs.getInt("technical_skills_score"),
                        rs.getString("total_score")
                );
                list.add(grade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<GradeForInterns> getAllGradeForInternByProjectCodeAndType(String projectCode, String type) {
        List<GradeForInterns> list = new ArrayList<>();
        String query = "SELECT \n"
                + "    ROW_NUMBER() OVER (ORDER BY e.intern_id DESC) AS stt,\n"
                + "    i.user_id,i.intern_id, a.full_name, p.position_name, pr.project_name,\n"
                + "    pr.project_code ,e.mentor_id ,e.comment, e.type, e.attitude_score, \n"
                + "    e.soft_skills_score, e.technical_skills_score,  e.total_score\n"
                + "FROM Evaluations e\n"
                + "JOIN Interns i ON i.intern_id = e.intern_id\n"
                + "JOIN Account a ON a.user_id = i.user_id \n"
                + "JOIN Positions p ON p.position_code = e.position_code\n"
                + "JOIN Projects pr ON pr.project_code = e.project_code\n"
                + "WHERE e.project_code = ? and e.type = ?\n"
                + "ORDER BY stt;";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
            ps.setString(2, type);
            rs = ps.executeQuery();
            while (rs.next()) {
                GradeForInterns grade = new GradeForInterns(
                        rs.getInt("stt"),
                        rs.getString("user_id"),
                        rs.getInt("intern_id"),
                        rs.getString("full_name"),
                        rs.getString("position_name"),
                        rs.getString("project_name"),
                        rs.getString("project_code"),
                        rs.getString("mentor_id"),
                        rs.getString("comment"),
                        rs.getString("type"),
                        rs.getInt("attitude_score"),
                        rs.getInt("soft_skills_score"),
                        rs.getInt("technical_skills_score"),
                        rs.getString("total_score")
                );
                list.add(grade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<GradeForInterns> getAllGradeForIntern() {
        List<GradeForInterns> list = new ArrayList<>();
        String query = "SELECT \n"
                + "    ROW_NUMBER() OVER (ORDER BY e.intern_id DESC) AS stt,\n"
                + "    i.user_id,i.intern_id, a.full_name, p.position_name, pr.project_name,\n"
                + "    pr.project_code ,e.mentor_id ,e.comment, e.type, e.attitude_score, \n"
                + "    e.soft_skills_score, e.technical_skills_score,  e.total_score\n"
                + "FROM Evaluations e\n"
                + "JOIN Interns i ON i.intern_id = e.intern_id\n"
                + "JOIN Account a ON a.user_id = i.user_id \n"
                + "JOIN Positions p ON p.position_code = e.position_code\n"
                + "JOIN Projects pr ON pr.project_code = e.project_code\n"
                + "ORDER BY stt;";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                GradeForInterns grade = new GradeForInterns(
                        rs.getInt("stt"),
                        rs.getString("user_id"),
                        rs.getInt("intern_id"),
                        rs.getString("full_name"),
                        rs.getString("position_name"),
                        rs.getString("project_name"),
                        rs.getString("project_code"),
                        rs.getString("mentor_id"),
                        rs.getString("comment"),
                        rs.getString("type"),
                        rs.getInt("attitude_score"),
                        rs.getInt("soft_skills_score"),
                        rs.getInt("technical_skills_score"),
                        rs.getString("total_score")
                );
                list.add(grade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Projects> getAllProject() {
        List<Projects> list = new ArrayList<>();
        String query = "SELECT * FROM Projects";
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

    public void failCandidateApply(String userId, String projectCode) {
        String query = "DELETE FROM [dbo].[Applications]\n"
                + "      WHERE applicant_id = ? and project_code = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            ps.setString(2, projectCode);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAllCandidateApplyByUserId(String userId) {
        String query = "DELETE FROM [dbo].[Applications]\n"
                + "      WHERE applicant_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CandidateApply getCandidateByUserId(String userId, String projectCode) {
        String query = "SELECT *\n"
                + "FROM Applications a\n"
                + "WHERE a.applicant_id = ? AND a.project_code =  ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            ps.setString(2, projectCode);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new CandidateApply(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getByte(13),
                        rs.getString(14));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updateRole(int role_id, String userId) {
        String query = "UPDATE [dbo].[Account]\n"
                + "   SET [role_id] = ?\n"
                + " WHERE [user_id] = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, role_id);
            ps.setString(2, userId);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void insertIntern(String userId, String projectCode, String mentorId, String positionCode) {
        String query = "INSERT INTO [dbo].[Interns]\n"
                + "           ([user_id]\n"
                + "           ,[project_code]\n"
                + "           ,[mentor_id]\n"
                + "           ,[position_code])\n"
                + "     VALUES(?,?,?,?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            ps.setString(2, projectCode);
            ps.setString(3, mentorId);
            ps.setString(4, positionCode);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }

    public int updateStatus(String status, String userId) {
        String query = "UPDATE [dbo].[Applications]\n"
                + "   SET [status] = ?\n"
                + " WHERE [applicant_id] = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, status);
            ps.setString(2, userId);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
//
//    public static void main(String[] args) {
//        EvaluationDAO dao = new EvaluationDAO();
//        List<GradeForInterns> list = dao.getAllGradeForInternByProjectCode("PRJ001", "Midterm");
//        for (GradeForInterns li : list) {
//            System.out.println(li);
//        }
//    }
}
