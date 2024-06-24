package models;

import java.sql.Date;
import java.util.List;
import models.Positions;

public class ProjectWithPositions {

    private String projectCode;
    private String projectName;
    private String mentorId;
    private String projectImage;
    private String description;
    private Date projectStartDay;
    private Date projectEndDay;

    private List<Positions> positions;

    public ProjectWithPositions() {
    }

    public ProjectWithPositions(String projectCode, String projectName, String mentorId, String projectImage, String description, Date projectStartDay, Date projectEndDay, List<Positions> positions) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.mentorId = mentorId;
        this.projectImage = projectImage;
        this.description = description;
        this.projectStartDay = projectStartDay;
        this.projectEndDay = projectEndDay;
        this.positions = positions;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getMentorId() {
        return mentorId;
    }

    public void setMentorId(String mentorId) {
        this.mentorId = mentorId;
    }

    public String getProjectImage() {
        return projectImage;
    }

    public void setProjectImage(String projectImage) {
        this.projectImage = projectImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getProjectStartDay() {
        return projectStartDay;
    }

    public void setProjectStartDay(Date projectStartDay) {
        this.projectStartDay = projectStartDay;
    }

    public Date getProjectEndDay() {
        return projectEndDay;
    }

    public void setProjectEndDay(Date projectEndDay) {
        this.projectEndDay = projectEndDay;
    }

    public List<Positions> getPositions() {
        return positions;
    }

    public void setPositions(List<Positions> positions) {
        this.positions = positions;
    }

    

}
