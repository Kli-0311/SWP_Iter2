/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class Reports {
    private int reportId;
    private int internId;
    private int week;
    private String report;
    private String reportLink;
    private String mentorId;
    private String projectCode; 

    public Reports(int reportId, int internId, int week, String report, String reportLink, String mentorId, String projectCode) {
        this.reportId = reportId;
        this.internId = internId;
        this.week = week;
        this.report = report;
        this.reportLink = reportLink;
        this.mentorId = mentorId;
        this.projectCode = projectCode; 
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getInternId() {
        return internId;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getReportLink() {
        return reportLink;
    }

    public void setReportLink(String reportLink) {
        this.reportLink = reportLink;
    }

    public String getMentorId() {
        return mentorId;
    }

    public void setMentorId(String mentorId) {
        this.mentorId = mentorId;
    }

    public String getProjectCode() { 
        return projectCode;
    }

    public void setProjectCode(String projectCode) { 
        this.projectCode = projectCode;
    }
}


