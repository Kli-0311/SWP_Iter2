/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author admin
 */
public class GradeForInterns {

    private int stt;
    private String rollNumber;
    private int internId;
    private String fullName;
    private String positionName;
    private String projectName;
    private String projectCode;
    private String mentorId;
    private String comment;
    private String type;
    private int attitude_score;
    private int soft_skills_score;
    private int technical_skills_score;
    private String total_score;

    public GradeForInterns() {
    }

    public GradeForInterns(int stt, String rollNumber, int internId, String fullName, String positionName, String projectName, String projectCode, String mentorId, String comment, String type, int attitude_score, int soft_skills_score, int technical_skills_score, String total_score) {
        this.stt = stt;
        this.rollNumber = rollNumber;
        this.internId = internId;
        this.fullName = fullName;
        this.positionName = positionName;
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.mentorId = mentorId;
        this.comment = comment;
        this.type = type;
        this.attitude_score = attitude_score;
        this.soft_skills_score = soft_skills_score;
        this.technical_skills_score = technical_skills_score;
        this.total_score = total_score;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public int getInternId() {
        return internId;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAttitude_score() {
        return attitude_score;
    }

    public void setAttitude_score(int attitude_score) {
        this.attitude_score = attitude_score;
    }

    public int getSoft_skills_score() {
        return soft_skills_score;
    }

    public void setSoft_skills_score(int soft_skills_score) {
        this.soft_skills_score = soft_skills_score;
    }

    public int getTechnical_skills_score() {
        return technical_skills_score;
    }

    public void setTechnical_skills_score(int technical_skills_score) {
        this.technical_skills_score = technical_skills_score;
    }

    public String getTotal_score() {
        return total_score;
    }

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
    }

   

}
