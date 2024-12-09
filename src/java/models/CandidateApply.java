/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author admin
 */
public class CandidateApply {

    private int appli_id;
    private String userId;
    private String username;
    private String projectCode;
    private String positionCode;
    private String status;
    private String full_name;
    private Date dob;
    private String gender;
    private String phone_number;
    private String avatar;
    private String specialization;
    private byte is_active;
    private String certificate;

    public CandidateApply() {
    }

    public CandidateApply(String userId, String full_name, String specialization) {
        this.userId = userId;
        this.full_name = full_name;
        this.specialization = specialization;

    }

    public CandidateApply(String username, String projectCode, String positionCode, String status, String userId, String full_name, Date dob, String gender, String phone_number, String specialization) {
        this.username = username;
        this.projectCode = projectCode;
        this.positionCode = positionCode;
        this.status = status;
        this.userId = userId;
        this.full_name = full_name;
        this.dob = dob;
        this.gender = gender;
        this.phone_number = phone_number;
        this.specialization = specialization;
    }

    public CandidateApply(int appli_id, String userId, String username, String projectCode, String positionCode, String status, String full_name, Date dob, String gender, String phone_number, String avatar, String specialization, byte is_active, String certificate) {
        this.appli_id = appli_id;
        this.userId = userId;
        this.username = username;
        this.projectCode = projectCode;
        this.positionCode = positionCode;
        this.status = status;
        this.full_name = full_name;
        this.dob = dob;
        this.gender = gender;
        this.phone_number = phone_number;
        this.avatar = avatar;
        this.specialization = specialization;
        this.is_active = is_active;
        this.certificate = certificate;
    }

    public int getAppli_id() {
        return appli_id;
    }

    public void setAppli_id(int appli_id) {
        this.appli_id = appli_id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFull_name() {
        return full_name;
    }public void setFull_name(String full_name) {
        this.full_name = full_name;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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

    public byte getIs_active() {
        return is_active;
    }

    public void setIs_active(byte is_active) {
        this.is_active = is_active;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }
}