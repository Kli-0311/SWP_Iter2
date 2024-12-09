/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ADM
 */
public class InternInformation {
    private int id;
    private String userId;
    private String fullName;
    private String specialization;
    private String positionName;
    private String avatar;
    private String dob;
    private String gender;
    private String phone;
    private boolean active;

    public InternInformation() {
    }

    public InternInformation(int id, String userId, String fullName, String specialization, String positionName, String avatar, String dob, String gender, String phone, boolean active) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.specialization = specialization;
        this.positionName = positionName;
        this.avatar = avatar;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "InternInformation{" + "id=" + id + ", userId=" + userId + ", fullName=" + fullName + ", specialization=" + specialization + ", positionName=" + positionName + ", avatar=" + avatar + ", dob=" + dob + ", gender=" + gender + ", phone=" + phone + ", active=" + active + '}';
    }
    
}
