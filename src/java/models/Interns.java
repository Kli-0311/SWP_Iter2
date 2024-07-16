/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author khanh
 */
public class Interns {
      private String internId;
    private String userId;
    private String projectCode;
    private String mentorId;
    private String positionCode; 

    public Interns() {
    }

    public Interns(String internId, String userId, String projectCode, String mentorId, String positionCode) { 
        this.internId = internId;
        this.userId = userId;
        this.projectCode = projectCode;
        this.mentorId = mentorId;
        this.positionCode = positionCode; 
    }

    public String getInternId() {
        return internId;
    }

    public void setInternId(String internId) {
        this.internId = internId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getMentorId() {
        return mentorId;
    }

    public void setMentorId(String mentorId) {
        this.mentorId = mentorId;
    }

    public String getPositionCode() { 
        return positionCode;
    }

    public void setPositionCode(String positionCode) { 
        this.positionCode = positionCode; 
    }

    @Override
    public String toString() {
        return "Interns{" + "internId=" + internId + ", userId=" + userId + ", projectCode=" + projectCode + ", mentorId=" + mentorId + ", positionCode=" + positionCode + '}';
    }

    
}

