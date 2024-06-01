package com.example.cmpe202project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@Document(collection = "courses")
public class Courses {

    @Id
    private int id;

    @NotEmpty(message = "Course name cannot be empty")
    private String courseName;

    @NotEmpty(message = "Semester cannot be empty")
    private String semester;
    private Integer semesterID;
    private String faculty;
    private Integer facultyID;
    private boolean isCurrent;
    private List<Integer> students;
    private List<Integer> assignmentList;
    private Map<String, Integer> grades;


    public Courses(int id, String courseName, String semester,  String faculty, Integer facultyID, Integer semesterID, List<Integer> students, List<Integer> assignmentList, Map<String, Integer> grades, boolean isCurrent) {
        this.id = id;
        this.courseName = courseName;
        this.semesterID = semesterID;
        this.semester = semester;
        this.faculty = faculty;
        this.facultyID = facultyID;
        this.students = students;
        this.assignmentList = assignmentList;
        this.grades= grades;
        this.isCurrent = isCurrent;
    }

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

    public Integer getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(Integer semesterID) {
        this.semesterID = semesterID;
    }

    public String getFaculty() {
        return faculty;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public List<Integer> getStudents() {
        return students;
    }

    public void setStudents(List<Integer> students) {
        this.students = students;
    }

    public List<Integer> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(List<Integer> assignmentList) {
        this.assignmentList = assignmentList;
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }

    public void setGrades(Map<String, Integer> grades) {
        this.grades = grades;
    }

    public Integer getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(Integer facultyID) {
        this.facultyID = facultyID;
    }

    public void addAssignment(Integer id) {
        assignmentList.add(id);
    }


}