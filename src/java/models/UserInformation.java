/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ADM
 */
// inter 3 
public class UserInformation {
    private String id;
    private String fullName;
    private String avatar;
    private String dob;
    private String gender;
    private String phone;
    private boolean active;
    private String nameProjects;
    private String projectcode;
    private String time;

    public UserInformation() {
    }

    public UserInformation(String id, String fullName, String avatar, String dob, String gender, String phone, boolean active, String nameProjects, String projectcode, String time) {
        this.id = id;
        this.fullName = fullName;
        this.avatar = avatar;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.active = active;
        this.nameProjects = nameProjects;
        this.projectcode = projectcode;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNameProjects() {
        return nameProjects;
    }

    public void setNameProjects(String nameProjects) {
        this.nameProjects = nameProjects;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "UserInformation{" + "id=" + id + ", fullName=" + fullName + ", avatar=" + avatar + ", dob=" + dob + ", gender=" + gender + ", phone=" + phone + ", active=" + active + ", nameProjects=" + nameProjects + ", projectcode=" + projectcode + ", time=" + time + '}';
    }

}