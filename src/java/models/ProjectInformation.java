/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ADM
 */
public class ProjectInformation {
    private String fullName;
    private String projectName;

    public ProjectInformation() {
    }

    public ProjectInformation(String fullName, String projectName) {
        this.fullName = fullName;
        this.projectName = projectName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "ProjectInformation{" + "fullName=" + fullName + ", projectName=" + projectName + '}';
    }
    
}
