/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Date;

/**
 *
 * @author khanh
 */
public class Projects {

    private String projectCode;
    private String projectName;
    private String mentorId;
    private String projectDetails;
    private String projectImg;
    private Date projectStartDay;
    private Date projectEndDay;

    public Projects() {
    }

    public Projects(String projectCode, String projectName, String mentorId, String projectDetails, String projectImg, Date projectStartDay, Date projectEndDay) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.mentorId = mentorId;
        this.projectDetails = projectDetails;
        this.projectImg = projectImg;
        this.projectStartDay = projectStartDay;
        this.projectEndDay = projectEndDay;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getMentorId() {
        return mentorId;
    }

    public void setMentorId(String mentorId) {
        this.mentorId = mentorId;
    }

    public String getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(String projectDetails) {
        this.projectDetails = projectDetails;
    }

    public String getProjectImg() {
        return projectImg;
    }

    public void setProjectImg(String projectImg) {
        this.projectImg = projectImg;
    }

    public Date getProjectStartDay() {
        return projectStartDay;
    }

    public void setProjectStartDay(Date projectStartDay) {
        this.projectStartDay = projectStartDay;
    }

    public Date getProjectEndDay() {
        return projectEndDay;
    }

    public void setProjectEndDay(Date projectEndDay) {
        this.projectEndDay = projectEndDay;
    }

    
}