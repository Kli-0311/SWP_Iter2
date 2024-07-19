/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ReportGroup {
    private int internId;
        private String fullName;
        private String projectCode;
        private String positionCode;
        private List<WeeklyReport> weeklyReports;

    public ReportGroup() {
    }

    public ReportGroup(int internId, String fullName, String projectCode, String positionCode, List<WeeklyReport> weeklyReports) {
        this.internId = internId;
        this.fullName = fullName;
        this.projectCode = projectCode;
        this.positionCode = positionCode;
        this.weeklyReports = weeklyReports;
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

    public List<WeeklyReport> getWeeklyReports() {
        return weeklyReports;
    }

    public void setWeeklyReports(List<WeeklyReport> weeklyReports) {
        this.weeklyReports = weeklyReports;
    }

    @Override
    public String toString() {
        return "ReportGroup{" + "internId=" + internId + ", fullName=" + fullName + ", projectCode=" + projectCode + ", positionCode=" + positionCode + ", weeklyReports=" + weeklyReports + '}';
    }
        
}
