/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ADM
 */
public class MentorDetail {
    private String userId;
    private String fullName;
    private String avatar;
    private String username;
    private String gender; 

    public MentorDetail() {
    }

    public MentorDetail(String userId, String fullName, String avatar, String username, String gender) {
        this.userId = userId;
        this.fullName = fullName;
        this.avatar = avatar;
        this.username = username;
        this.gender = gender;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "MentorDetail{" + "userId=" + userId + ", fullName=" + fullName + ", avatar=" + avatar + ", username=" + username + ", gender=" + gender + '}';
    }

}