package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Account;
import models.AccountWithInternSchedule;
import models.Role;

/**
 *
 * @author ADMIN
 */
public class AdminDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public static void main(String[] args) {
        AdminDAO dao = new AdminDAO();
        List<Role> d = dao.getAllRole();
        for (Role l : d) {
            System.out.println(l);
        }
    }

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
            e.printStackTrace(); // Log the exception
        } finally {
            closeResources();
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
            e.printStackTrace(); // Log the exception
        } finally {
            closeResources();
        }
    }

    //METHOD to fetch a single account by username
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
            e.printStackTrace(); // Log the exception
        } finally {
            closeResources();
        }
        return null;
    }

    //METHOD 
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
            e.printStackTrace(); // Log the exception
        } finally {
            closeResources();
        }
        return roles;
    }

    //METHOD to check Account is exists or not
    public boolean checkAccountExists(String user_id, String username) {
        String query = "SELECT * FROM Account WHERE user_id = ? or username = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user_id);
            ps.setString(2, username);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return false;
        } finally {
            closeResources();
        }
    }

    //Check phone number is exists or not
    public boolean isPhoneNumberExists(String phoneNumber) {
        String query = "SELECT * FROM Account WHERE phone_number = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, phoneNumber);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return false;
        } finally {
            closeResources();
        }
    }

    // Sử dụng phương thức để lấy danh sách HR (Role_id = 3)
    public List<Account> getAllHR() {
        return getUsersByRoleId(3);
    }

    // Sử dụng phương thức để lấy danh sách Mentor (Role_id = 4)
    public List<Account> getAllMentor() {
        return getUsersByRoleId(4);
    }

    // Sử dụng phương thức để lấy danh sách Intern (Role_id = 5)
    public List<Account> getAllIntern() {
        return getUsersByRoleId(5);
    }

    // Sử dụng phương thức để lấy danh sách Candidate (Role_id = 6)
    public List<Account> getAllCandidate() {
        return getUsersByRoleId(6);
    }

    // Phương thức chung để lấy danh sách người dùng theo vai trò
    public List<Account> getUsersByRoleId(int roleId) {
        List<Account> list = new ArrayList<>();
        String query = "SELECT * FROM Account WHERE role_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, roleId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(
                        rs.getString("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getDate("dob"),
                        rs.getString("gender"),
                        rs.getString("phone_number"),
                        rs.getString("avatar"),
                        rs.getString("specialization"),
                        rs.getInt("role_id"),
                        rs.getByte("is_active")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        } finally {
            closeResources();
        }
        return list;
    }

    //Iter2
    //METHOD to select all Account 
    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String query = "SELECT * FROM Account";
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
            // Log the exception

        } finally {
            closeResources();
        }

        return list;
    }

    //METHOD search Account by username
    public List<Account> searchAccountByUsername(String username) {
        List<Account> accountList = new ArrayList<>(); // Danh sách kết quả tìm kiếm
        String query = "SELECT * FROM Account WHERE username LIKE ?";
        try {
            conn = new DBContext().getConnection();   // Lấy kết nối tới cơ sở dữ liệu từ DBContext
            ps = conn.prepareStatement(query);   // Chuẩn bị câu truy vấn
            ps.setString(1, "%" + username + "%");  // Thiết lập tham số cho câu truy vấn: %username%
            rs = ps.executeQuery(); // Thực thi truy vấn và lấy kết quả
            while (rs.next()) {
                accountList.add(new Account(
                        rs.getString("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getDate("dob"),
                        rs.getString("gender"),
                        rs.getString("phone_number"),
                        rs.getString("avatar"),
                        rs.getString("specialization"),
                        rs.getInt("role_id"),
                        rs.getByte("is_active")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        } finally {
            closeResources();
        }
        return accountList;
    }

    // Method to fetch a single account by user_id
    public Account getAccountById(String username) {
        String query = "select * from Account where user_id = ?";
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
            e.printStackTrace(); // Log the exception
        } finally {
            closeResources();
        }
        return null;
    }

    // Method to deactivate an account    
    public void inactiveAccount(Account account) {
        try {
            String query = "UPDATE Account SET is_active = ? WHERE user_id = ?";
            conn = new DBContext().getConnection(); // Lấy kết nối từ đối tượng DBContext
            ps = conn.prepareStatement(query);

            // Thiết lập tham số SQL từ đối tượng Account
            ps.setByte(1, account.getIs_active());
            ps.setString(2, account.getUser_id());

            // Thực hiện câu lệnh SQL
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        } finally {
            closeResources();
        }
    }

    //SEARCH ACCOUNT OF USER
    public List<Account> searchUserInfor(String username) {
        List<Account> accountList = new ArrayList<>(); // Danh sách kết quả tìm kiếm
        String query = "SELECT * FROM Account WHERE username LIKE ?";
        try {
            conn = new DBContext().getConnection();   // Lấy kết nối tới cơ sở dữ liệu từ DBContext
            ps = conn.prepareStatement(query);   // Chuẩn bị câu truy vấn
            ps.setString(1, "%" + username + "%");  // Thiết lập tham số cho câu truy vấn: %username%
            rs = ps.executeQuery(); // Thực thi truy vấn và lấy kết quả
            while (rs.next()) {
                accountList.add(new Account(
                        rs.getString("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getDate("dob"),
                        rs.getString("gender"),
                        rs.getString("phone_number"),
                        rs.getString("avatar"),
                        rs.getString("specialization"),
                        rs.getInt("role_id"),
                        rs.getByte("is_active")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        } finally {
            closeResources();
        }
        return accountList;
    }

    public void saveAccountToDatabase(Account account) {
        String query = "INSERT INTO Account (user_id, username, password, full_name, role_id) VALUES (?, ?, ?, ?, ?)";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, account.getUser_id());
            ps.setString(2, account.getUsername());
            ps.setString(3, account.getPassword());
            ps.setString(4, account.getFull_name());
            ps.setInt(5, 6); // Set role_id to 6 by default

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        } finally {
            closeResources();
        }
    }

    // Close resources to avoid memory leaks
    private void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createScheduleForCandidate(String user_id, Date startDate, Date endDate) {
        String query = "INSERT INTO InternSchedule (user_id, start_date, end_date) VALUES (?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user_id);
            ps.setDate(2, (java.sql.Date) startDate);
            ps.setDate(3, (java.sql.Date) endDate);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean updateScheduleForCandidate(String user_id, Date startDate, Date endDate) {
        String query = "UPDATE InternSchedule SET start_date = ?, end_date = ? WHERE user_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setDate(1, (java.sql.Date) startDate);
            ps.setDate(2, (java.sql.Date) endDate);
            ps.setString(3, user_id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasInternSchedule(String user_id) {
        String query = "SELECT * FROM InternSchedule WHERE user_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user_id);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Account> getCandidatesWithoutSchedule() {
        List<Account> list = new ArrayList<>();
        String query = "SELECT a.*\n"
                + "FROM Account a\n"
                + "LEFT JOIN InternSchedule i ON a.user_id = i.user_id\n"
                + "WHERE a.role_id = 6 AND i.user_id IS NULL or a.role_id = 5 AND i.user_id IS NULL;";
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

    public List<AccountWithInternSchedule> getCandidatesWithSchedule() {
        List<AccountWithInternSchedule> list = new ArrayList<>();
        String query = "SELECT a.*, s.start_date, s.end_date \n"
                + "FROM Account a JOIN InternSchedule s ON a.user_id = s.user_id WHERE a.role_id = 6 or a.role_id = 5\n"
                + "order by s.schedule_id desc";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new AccountWithInternSchedule(rs.getString(1),
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
                        rs.getDate(14)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void insertIPAddressRange(String userId, String startIpAddress, String endIpAddress) {
        String query = "INSERT INTO WifiIPAddress (user_id, created_at, startIpAddress, endIpAddress) VALUES (?, GETDATE(), ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            ps.setString(2, startIpAddress);
            ps.setString(3, endIpAddress);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public String getLatestStartIPAddress() {
        String query = "SELECT TOP 1 startIpAddress FROM WifiIPAddress ORDER BY created_at DESC";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("startIpAddress");
            }
        } catch (Exception e) {
        }
        return null;
    }
    public String getLatestEndIPAddress() {
        String query = "SELECT TOP 1 endIpAddress FROM WifiIPAddress ORDER BY created_at DESC";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("endIpAddress");
            }
        } catch (Exception e) {
        }
        return null;
    }

}
