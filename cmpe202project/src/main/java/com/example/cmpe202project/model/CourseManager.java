package com.example.cmpe202project.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class CourseManager {
    @Id
    private int id;
    private String courseName;  // Stores the name of the course
    private String semester;  // Stores the semester information
    private int semesterID;   // Used to determine if the course is current
    private boolean isCurrent;  // Indicates if the course is currently active
    private boolean published;  // Indicates if the course is published

    public CourseManager() {}

    public CourseManager(int id, String courseName, String semester, int semesterID, boolean isCurrent, boolean published) {
        this.id = id;
        this.courseName = courseName;
        this.semester = semester;
        this.semesterID = semesterID;
        this.isCurrent = isCurrent;
        this.published = published;
    }

    // Standard getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
