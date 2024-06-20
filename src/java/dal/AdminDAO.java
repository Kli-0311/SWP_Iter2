package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Account;
import models.Role;

/**
 *
 * @author ADMIN
 */
public class AdminDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void createAccountCandidate(String user_id, String username, int role_id) {
        String query = "INSERT INTO Account (user_id, username, role_id, is_active) VALUES (?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user_id);
            ps.setString(2, username);
            ps.setInt(3, role_id);
            ps.setBoolean(4, true);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void createAccountUser(String user_id, String username, int role_id, String full_name, Date dob, String gender, String phone_number, String avatar, String password) {
        String query = "INSERT INTO Account (user_id, username, password, full_name, dob, gender, phone_number, avatar, role_id, is_active) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user_id);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, full_name);
            ps.setDate(5, new java.sql.Date(dob.getTime()));
            ps.setString(6, gender);
            ps.setString(7, phone_number);
            ps.setString(8, "img/" + avatar);
            ps.setInt(9, role_id);
            ps.setBoolean(10, true);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Account getAccountByUsername(String username) {
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

    public List<Role> getAllRole() {
        List<Role> roles = new ArrayList<>();
        String query = "SELECT role_id, role_name FROM Role";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                roles.add(new Role(rs.getInt("role_id"), rs.getString("role_name")));
            }
        } catch (Exception e) {
        }
        return roles;
    }

    public boolean checkAccountExists(String user_id) {
        String query = "SELECT * FROM Account WHERE user_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user_id);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    public List<Account> getAllCandidate() {
        List<Account> list = new ArrayList<>();
        String query = "Select *\n"
                + "from Account a\n"
                + "Where role_id = 6";
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
                        rs.getByte(11)
                ));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<Account> getAllMentor() {
        List<Account> list = new ArrayList<>();
        String query = "Select *\n"
                + "from Account a\n"
                + "Where role_id = 4";
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
                        rs.getByte(11)
                ));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<Account> getAllIntern() {
        List<Account> list = new ArrayList<>();
        String query = "Select *\n"
                + "from Account a\n"
                + "Where role_id = 5";
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
                        rs.getByte(11)
                ));
            }
        } catch (Exception e) {

        }
        return list;
    }

    
    //METHOD TO GET ALL ACCOUNT
    public List<Account> getAllAccount() {
        return null;
    }

}
