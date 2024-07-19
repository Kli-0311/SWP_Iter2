/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ADMIN
 */
public class WeeklyReport {
    private int week;
    private String reportLink;

    public WeeklyReport() {
    }

    public WeeklyReport(int week, String reportLink) {
        this.week = week;
        this.reportLink = reportLink;
    }

   

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getReportLink() {
        return reportLink;
    }

    public void setReportLink(String reportLink) {
        this.reportLink = reportLink;
    }

    @Override
    public String toString() {
        return "WeeklyReport{" + "week=" + week + ", reportLink=" + reportLink + '}';
    }

}
