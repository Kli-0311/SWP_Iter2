/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ADM
 */
public class ReportsMentor {
    private int internId;
    private String fullName;
    private String projectCode;
    private String positionCode;
    private int week;
    private String report;
    private String reportLink;
    

    public ReportsMentor() {
    }

    public ReportsMentor(int internId, String fullName, String projectCode, String positionCode, int week, String report, String reportLink) {
        this.internId = internId;
        this.fullName = fullName;
        this.projectCode = projectCode;
        this.positionCode = positionCode;
        this.week = week;
        this.report = report;
        this.reportLink = reportLink;
        
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

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
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

    @Override
    public String toString() {
        return "ReportsMentor{" + "internId=" + internId + ", fullName=" + fullName + ", projectCode=" + projectCode + ", positionCode=" + positionCode + ", week=" + week + ", report=" + report + ", reportLink=" + reportLink + '}';
    }

    

    
    

}