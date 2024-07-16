
package models;

import java.sql.Date;

public class Notes {

    private int noteId;
    private int internId;
    private String noteContent;
    private Date createdAt;


    public Notes(int noteId, int internId, String noteContent, Date createdAt) {
        this.noteId = noteId;
        this.internId = internId;
        this.noteContent = noteContent;
        this.createdAt = createdAt;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getInternId() {
        return internId;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    
}
