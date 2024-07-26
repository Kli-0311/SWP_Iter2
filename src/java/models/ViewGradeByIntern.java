/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ADM
 */
public class ViewGradeByIntern {
    private String internId;
    private String mentorName;
    private String type;
    private int attitudeScore;
    private int softSkillsScore;
    private int technicalSkillsScore;
    private String totalScore;
    private String comment;
    private String projectName;
    private String positionName;

    public ViewGradeByIntern() {
    }

    public ViewGradeByIntern(String internId, String mentorName, String type, int attitudeScore, int softSkillsScore, int technicalSkillsScore, String totalScore, String comment, String projectName, String positionName) {
        this.internId = internId;
        this.mentorName = mentorName;
        this.type = type;
        this.attitudeScore = attitudeScore;
        this.softSkillsScore = softSkillsScore;
        this.technicalSkillsScore = technicalSkillsScore;
        this.totalScore = totalScore;
        this.comment = comment;
        this.projectName = projectName;
        this.positionName = positionName;
    }

    public String getInternId() {
        return internId;
    }

    public void setInternId(String internId) {
        this.internId = internId;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAttitudeScore() {
        return attitudeScore;
    }

    public void setAttitudeScore(int attitudeScore) {
        this.attitudeScore = attitudeScore;
    }

    public int getSoftSkillsScore() {
        return softSkillsScore;
    }

    public void setSoftSkillsScore(int softSkillsScore) {
        this.softSkillsScore = softSkillsScore;
    }

    public int getTechnicalSkillsScore() {
        return technicalSkillsScore;
    }

    public void setTechnicalSkillsScore(int technicalSkillsScore) {
        this.technicalSkillsScore = technicalSkillsScore;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    @Override
    public String toString() {
        return "ViewGradeByIntern{" + "internId=" + internId + ", mentorName=" + mentorName + ", type=" + type + ", attitudeScore=" + attitudeScore + ", softSkillsScore=" + softSkillsScore + ", technicalSkillsScore=" + technicalSkillsScore + ", totalScore=" + totalScore + ", comment=" + comment + ", projectName=" + projectName + ", positionName=" + positionName + '}';
    }

}