/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.MentorDAO;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class InternWithInternSchedule {

    private String user_id;
    private String username;
    private String password;
    private String full_name;
    private Date dob;
    private String gender;
    private String phone_number;
    private String avatar;
    private String specialization;
    private int role_id;
    private byte is_active;
    private Date start_date;
    private Date end_date;
    private int intern_id;
    private String project_code;
    private String position_code;

    public InternWithInternSchedule(String user_id, String username, String password, String full_name, Date dob, String gender, String phone_number, String avatar, String specialization, int role_id, byte is_active, Date start_date, Date end_date, int intern_id, String project_code, String position_code) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.dob = dob;
        this.gender = gender;
        this.phone_number = phone_number;
        this.avatar = avatar;
        this.specialization = specialization;
        this.role_id = role_id;
        this.is_active = is_active;
        this.start_date = start_date;
        this.end_date = end_date;
        this.intern_id = intern_id;
        this.project_code = project_code;
        this.position_code = position_code;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
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

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public byte getIs_active() {
        return is_active;
    }

    public void setIs_active(byte is_active) {
        this.is_active = is_active;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getIntern_id() {
        return intern_id;
    }

    public void setIntern_id(int intern_id) {
        this.intern_id = intern_id;
    }

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    public String getPosition_code() {
        return position_code;
    }

    public void setPosition_code(String position_code) {
        this.position_code = position_code;
    }

    
    public int calculateWorkingDays() {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(this.start_date);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(this.end_date);

        int workingDays = 0;

        while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()) {
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                    && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                workingDays++;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        }

        return workingDays;
    }
    
}
