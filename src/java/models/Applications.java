/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author khanh
 */
public class Applications {

    private int application_id;
    private String applicant_id;
    private String projectCode;
    private String positionCode;
    private String status;

    public Applications() {
    }

    public Applications(String projectCode) {
        this.projectCode = projectCode;
    }

    public Applications(int application_id, String applicant_id, String projectCode, String positionCode, String status) {
        this.application_id = application_id;
        this.applicant_id = applicant_id;
        this.projectCode = projectCode;
        this.positionCode = positionCode;
        this.status = status;
    }

    public int getApplication_id() {
        return application_id;
    }

    public void setApplication_id(int application_id) {
        this.application_id = application_id;
    }

    public String getApplicant_id() {
        return applicant_id;
    }

    public void setApplicant_id(String applicant_id) {
        this.applicant_id = applicant_id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
