package models;

import java.util.Date;

public class Certificate {
    private int cerId;
    private String cerName;
    private Date issueDate;
    private String projectCode;  
    private String cerImg;
    private String cerLink;
    private int internId;
    private String senderId;


    public Certificate() {}

    public Certificate(int cerId, String cerName, Date issueDate, String projectCode, String cerImg, String cerLink, int internId, String senderId) {
        this.cerId = cerId;
        this.cerName = cerName;
        this.issueDate = issueDate;
        this.projectCode = projectCode;
        this.cerImg = cerImg;
        this.cerLink = cerLink;
        this.internId = internId;
        this.senderId = senderId;
    }

    // Getters and Setters
    public int getCerId() {
        return cerId;
    }

    public void setCerId(int cerId) {
        this.cerId = cerId;
    }

    public String getCerName() {
        return cerName;
    }

    public void setCerName(String cerName) {
        this.cerName = cerName;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCerImg() {
        return cerImg;
    }

    public void setCerImg(String cerImg) {
        this.cerImg = cerImg;
    }

    public String getCerLink() {
        return cerLink;
    }

    public void setCerLink(String cerLink) {
        this.cerLink = cerLink;
    }

    public int getInternId() {
        return internId;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
}
