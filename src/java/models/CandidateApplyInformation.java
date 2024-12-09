/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author ADM
 */
public class CandidateApplyInformation {
    private String userId;
    private String fullName;
    private String avatar;
    private String specialization;
    private String projectName;
    private String positionName;
    private Date dob;
    private String gender;
    private String phoneNumber;
    private String status;
    private int applicationId;

    public CandidateApplyInformation() {
    }

    public CandidateApplyInformation(String userId, String fullName, String avatar, String specialization, String projectName, String positionName, Date dob, String gender, String phoneNumber, String status, int applicationId) {
        this.userId = userId;
        this.fullName = fullName;
        this.avatar = avatar;
        this.specialization = specialization;
        this.projectName = projectName;
        this.positionName = positionName;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.applicationId = applicationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public String toString() {
        return "CandidateApplyInformation{" + "userId=" + userId + ", fullName=" + fullName + ", avatar=" + avatar + ", specialization=" + specialization + ", projectName=" + projectName + ", positionName=" + positionName + ", dob=" + dob + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", status=" + status + ", applicationId=" + applicationId + '}';
    }
    
}
