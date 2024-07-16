/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author khanh
 */
public class Positions {
    private String positionCode;
    private String positionName;
    private int positionCount;
    
     public Positions() {
    }

    public Positions(String positionCode, String positionName, int positionCount) {
        this.positionCode = positionCode;
        this.positionName = positionName;
        this.positionCount = positionCount;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getPositionCount() {
        return positionCount;
    }

    public void setPositionCount(int positionCount) {
        this.positionCount = positionCount;
    }
    
    
}
