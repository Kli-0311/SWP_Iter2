package models;

import java.sql.Date;


public class AttendanceList {
    private int rowNumber;
    private int attendanceId;
    private int internId;
    private String userId; 
    private Date date;
    private String status;

    public AttendanceList() {
    }

    public AttendanceList(int rowNumber, int attendanceId, int internId, String userId, Date date, String status) {
        this.rowNumber = rowNumber;
        this.attendanceId = attendanceId;
        this.internId = internId;
        this.userId = userId;
        this.date = date;
        this.status = status;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getAttendanceListId() {
        return attendanceId;
    }

    public void setAttendanceListId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getInternId() {
        return internId;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AttendanceList{" + "rowNumber=" + rowNumber + ", attendanceId=" + attendanceId + ", internId=" + internId + ", userId=" + userId + ", date=" + date + ", status=" + status + '}';
    }
   

}