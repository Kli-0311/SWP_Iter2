/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author ADM
 */
public class ViewGradeByNameIntern {
   
    private String internName;

    public ViewGradeByNameIntern() {
    }

    public ViewGradeByNameIntern( String internName) {
      
        this.internName = internName;
    }

   
    public String getInternName() {
        return internName;
    }

    public void setInternName(String internName) {
        this.internName = internName;
    }

    @Override
    public String toString() {
        return "ViewGradeByNameIntern{  internName=" + internName + '}';
    }
    
}
