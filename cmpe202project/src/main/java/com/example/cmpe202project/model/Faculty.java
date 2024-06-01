package com.example.cmpe202project.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "faculty")
public class Faculty extends User {

    private List<Courses> courseList;

    public Faculty() {
    }

    public Faculty(int id, String firstName, String lastName, String email, String password, List<Courses> courseList) {
        super(id, firstName, lastName, email, password);
        this.courseList = courseList;
    }

    public void setCourseList(List<Courses> courseList) {
        this.courseList = courseList;
    }

    public List<Courses> getCourseList() {
        return courseList;
    }

}