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
import models.Applications;
import models.CandidateApply;
import models.Notifications;

/**
 *
 * @author admin
 */
public class CandidateDAO extends DBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void saveApplication(String userId, String projectCode, String positionCode) {
        String query = "INSERT INTO Applications (applicant_id, project_code, position_code) "
                + "VALUES (?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            ps.setString(2, projectCode);
            ps.setString(3, positionCode);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public Applications getProjectCodeByApplicantId(String applicant_id) {
        String query = "Select project_code from Applications Where applicant_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, applicant_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Applications(rs.getString(1));
            }

        } catch (Exception e) {
        }
        return null; // Trả về null nếu không tìm thấy kết quả
    }

    public List<CandidateApply> getAllCandidateApplyByPositionCode(String positionCode) {
        List<CandidateApply> list = new ArrayList<>();
        String query = "select u.user_id, u.full_name,u.specialization\n"
                + "from Account u\n"
                + "join Applications a on a.applicant_id = u.user_id\n"
                + "Where a.position_code LIKE ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, positionCode);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new CandidateApply(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<CandidateApply> getAllCandidateApplyByProjectCode(String projectCode) {
        List<CandidateApply> list = new ArrayList<>();
        String query = "select a.project_code,a.position_code,a.status, u.user_id, u.full_name,u.dob,u.gender,u.phone_number,u.specialization\n"
                + "from Account u\n"
                + "join Applications a on a.applicant_id = u.user_id\n"
                + "Where a.project_code LIKE ?";
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
                        rs.getDate(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)));
            }

        } catch (Exception e) {

        }
        return list;
    }
    //view lich phong van
    public Notifications getInterviewScheduleByProjectCode(String projectCode) {
        String query = "select * from Notifications n\n"
                + "where n.project_code LIKE ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, projectCode);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Notifications(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getTime(8),
                        rs.getDate(9),
                        rs.getString(10),
                        rs.getString(11));
            }
        } catch (Exception e) {

        }
        return null;
    }
}
