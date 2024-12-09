/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author admin
 */
public class Evaluations {

    private int evaluation_id;
    private int internId;
    private String mentorId;
    private String type;
    private int attitude_score;
    private int soft_skills_score;
    private int technical_skills_score;
    private String total_score;
    private String comment;
    private String projectCode;
    private String positionCode;

    public Evaluations() {
    }

    public Evaluations(int evaluation_id, int internId, String mentorId, String type, int attitude_score, int soft_skills_score, int technical_skills_score, String total_score, String comment, String projectCode, String positionCode) {
        this.evaluation_id = evaluation_id;
        this.internId = internId;
        this.mentorId = mentorId;
        this.type = type;
        this.attitude_score = attitude_score;
        this.soft_skills_score = soft_skills_score;
        this.technical_skills_score = technical_skills_score;
        this.total_score = total_score;
        this.comment = comment;
        this.projectCode = projectCode;
        this.positionCode = positionCode;
    }

    public int getEvaluation_id() {
        return evaluation_id;
    }

    public void setEvaluation_id(int evaluation_id) {
        this.evaluation_id = evaluation_id;
    }

    public int getInternId() {
        return internId;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    public String getMentorId() {
        return mentorId;
    }

    public void setMentorId(String mentorId) {
        this.mentorId = mentorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAttitude_score() {
        return attitude_score;
    }

    public void setAttitude_score(int attitude_score) {
        this.attitude_score = attitude_score;
    }

    public int getSoft_skills_score() {
        return soft_skills_score;
    }

    public void setSoft_skills_score(int soft_skills_score) {
        this.soft_skills_score = soft_skills_score;
    }

    public int getTechnical_skills_score() {
        return technical_skills_score;
    }

    public void setTechnical_skills_score(int technical_skills_score) {
        this.technical_skills_score = technical_skills_score;
    }

    public String getTotal_score() {
        return total_score;
    }

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

   
}
