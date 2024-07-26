package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import models.Account;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFileExcel {

    public static List<Account> readExcel(File file) {
        List<Account> listOfAccounts = new ArrayList<>();

        try (FileInputStream excelFile = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(excelFile)) {

            Sheet datatypeSheet = workbook.getSheetAt(0);
            DataFormatter fmt = new DataFormatter();
            Iterator<Row> iterator = datatypeSheet.iterator();
            Row firstRow = iterator.next(); // Bỏ qua dòng tiêu đề

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Account account = new Account();

                // Kiểm tra không có ô nào được bỏ trống
                if (currentRow.getCell(0) == null ||
                    currentRow.getCell(1) == null ||
                    currentRow.getCell(2) == null ||
                    currentRow.getCell(3) == null) {
                    System.out.println("Invalid row format: Empty cells found");
                    continue; // Bỏ qua dòng có ô trống và không thêm vào danh sách
                }

                account.setUser_id(fmt.formatCellValue(currentRow.getCell(0)));
                account.setUsername(fmt.formatCellValue(currentRow.getCell(1)));
                account.setPassword(fmt.formatCellValue(currentRow.getCell(2)));
                account.setFull_name(fmt.formatCellValue(currentRow.getCell(3)));

                // Kiểm tra User_id và Username đã tồn tại trong DB
                if (isUserExists(account.getUser_id(), account.getUsername())) {
                    // Thực hiện xử lý thông báo hoặc lưu lại tài khoản đã tồn tại
                    System.out.println("User_id or Username already exists: " + account.getUser_id() + ", " + account.getUsername());
                    continue; // Bỏ qua tài khoản đã tồn tại và không thêm vào danh sách
                }

                // Kiểm tra Username có đuôi @fpt.edu.vn
                if (!isValidEmailDomain(account.getUsername())) {
                    // Thực hiện xử lý thông báo hoặc bỏ qua tài khoản không hợp lệ
                    System.out.println("Username must end with @fpt.edu.vn: " + account.getUsername());
                    continue; // Bỏ qua tài khoản không hợp lệ và không thêm vào danh sách
                }

                // Kiểm tra định dạng Password
                if (!isValidPasswordFormat(account.getPassword())) {
                    // Thực hiện xử lý thông báo hoặc bỏ qua tài khoản không hợp lệ
                    System.out.println("Invalid password format for user: " + account.getUsername());
                    continue; // Bỏ qua tài khoản không hợp lệ và không thêm vào danh sách
                }

                // Nếu tất cả các kiểm tra đều pass, thêm account vào danh sách
                listOfAccounts.add(account);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfAccounts;
    }

    private static boolean isUserExists(String userId, String username) {
        // Thực hiện kiểm tra User_id hoặc Username đã tồn tại trong DB
        // Sử dụng JDBC để thực hiện truy vấn tại đây
        // Trả về true nếu tồn tại, false nếu không tồn tại
        return false; // Giả sử trả về false nếu không tồn tại
    }

    private static boolean isValidEmailDomain(String username) {
        // Kiểm tra Username có đuôi @fpt.edu.vn
        return username.endsWith("@fpt.edu.vn");
    }

    private static boolean isValidPasswordFormat(String password) {
        // Kiểm tra định dạng Password
        // Password phải có ít nhất 8 ký tự, bao gồm chữ cái, số và ký tự đặc biệt
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return Pattern.compile(regex).matcher(password).matches();
    }
}
