package models;



import java.util.ArrayList;
import java.util.List;
import models.Account;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author NXC2003
 */
public class ProjectWithMembers {
    private String projectCode;
    private List<MemberWithPosition> members;

    public ProjectWithMembers(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public List<MemberWithPosition> getMembers() {
        return members;
    }

    public void setMembers(List<MemberWithPosition> members) {
        this.members = members;
    }
}