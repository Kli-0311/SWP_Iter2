/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import models.Account;

/**
 *
 * @author ADMIN
 */
public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Account login(String username, String password) {
        String query = "select * from Account where username = ? and password = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
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
        }
        return null;
    }

    public Account getAccount(String username, String password) {
        String query = "select * from Account where username = ? and password = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
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
        }
        return null;
    }

    public Account searchAccountByUsername(String username) {
        String query = "select * from Account where username = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
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

        }
        return null;
    }

    public void updateAccount(String userName, String password, String full_name, Date dob, String gender, String phone_number, String avatar, String specialization) {
        String query = "UPDATE Account SET password = ?,full_name = ?, dob = ?, gender = ?, phone_number = ?, avatar = ?, specialization = ?  WHERE username = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, full_name);
            ps.setDate(3, new java.sql.Date(dob.getTime()));
            ps.setString(4, gender);
            ps.setString(5, phone_number);
            ps.setString(6, "img/" + avatar);
            ps.setString(7, specialization);

            ps.setString(8, userName);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public String getRoleName(int role_id) {
        String query = "SELECT role_name FROM Role WHERE role_id = ?";
        String roleName = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, role_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                roleName = rs.getString("role_name");
            }
        } catch (Exception e) {

        }
        return roleName;
    }

    public void changeInfoOfUserPage(String username, String full_name, Date dob, String gender, String phone_number, String avatar, String specialization) {
        String query = "UPDATE Account\n"
                + "SET full_name = ?, dob = ? , gender = ?, phone_number = ?, avatar=?,  specialization = ? \n"
                + "WHERE username  = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, full_name);
            ps.setDate(2, new java.sql.Date(dob.getTime()));
            ps.setString(3, gender);
            ps.setString(4, phone_number);
            ps.setString(5, avatar);
            ps.setString(6, specialization);
            ps.setString(7, username);

            rs = ps.executeQuery();
        } catch (Exception e) {
        }
    }           

    public void updatePassByUserName(String username, String password) {
        String query = "UPDATE Account\n"
                + "SET password = ? \n"
                + "WHERE username = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, username);
            rs = ps.executeQuery();
        } catch (Exception e) {
        }
    }

    public boolean updatePasswordInDatabase(String email, String newPassword) {
        String query = "UPDATE Account SET password = ? WHERE username = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, newPassword);
            ps.setString(2, email);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
        }
        return false;
    }
    
     public boolean isEmailExist(String email) {
        String query = "SELECT 1 FROM Account WHERE username = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next(); // Trả về true nếu email tồn tại
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
    }

}
