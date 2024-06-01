package com.example.cmpe202project.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class HomepageCourse {
    @Id
    private String id;

    private String courseName;
    private String semester;
    private int semesterID;
    private boolean isCurrent;

    public HomepageCourse() {
    }

    public HomepageCourse( String courseName, String semester, int semesterID, boolean isCurrent) {

        this.courseName = courseName;
        this.semester = semester;
        this.semesterID = semesterID;
        this.isCurrent = isCurrent;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(int semesterID) {
        this.semesterID = semesterID;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }
}