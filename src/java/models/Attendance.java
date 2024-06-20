package models;

import java.sql.Date;


public class Attendance {
    private int attendanceId;
    private int internId;
    private String userId; 
    private Date date;
    private String status;

    public Attendance() {
    }

    public Attendance(int attendanceId, int internId, String userId, Date date, String status) {
        this.attendanceId = attendanceId;
        this.internId = internId;
        this.userId = userId;
        this.date = date;
        this.status = status;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
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
        return "Attendance{" + "attendanceId=" + attendanceId + ", internId=" + internId + ", userId=" + userId + ", date=" + date + ", status=" + status + '}';
    }

}

