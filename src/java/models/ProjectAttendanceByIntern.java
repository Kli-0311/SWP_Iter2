/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ADM
 */
public class ProjectAttendanceByIntern {
    private int intern_id;
    private String full_name; 
    private String project_name;

    public ProjectAttendanceByIntern() {
    }

    public ProjectAttendanceByIntern(int intern_id, String full_name, String project_name) {
        this.intern_id = intern_id;
        this.full_name = full_name;
        this.project_name = project_name;
    }
    

    public int getIntern_id() {
        return intern_id;
    }

    public void setIntern_id(int intern_id) {
        this.intern_id = intern_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    @Override
    public String toString() {
        return "ProjectAttendanceByIntern{" + "intern_id=" + intern_id + ", full_name=" + full_name + ", project_name=" + project_name + '}';
    }
    
    
}
