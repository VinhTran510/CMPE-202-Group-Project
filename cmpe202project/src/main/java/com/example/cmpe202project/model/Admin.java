package com.example.cmpe202project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "admin")
public class Admin extends User{
//    private List<Student> studentList;
//    private Map<Courses, Faculty> coursebyFaculty;

    public Admin() {
    }

//    public Admin(int id, String firstName, String lastName, String email, String password, List<Student> studentList, Map<Courses, Faculty> coursebyFaculty) {
//        super(id, firstName, lastName, email, password);
//        this.studentList = studentList;
//        this.coursebyFaculty = coursebyFaculty;
//    }
//
//    public List<Student> getStudentList() {
//        return studentList;
//    }
//
//    public void setStudentList(List<Student> profileInfo) {
//        this.studentList = studentList;
//    }
//
//    public Map<Courses, Faculty> getCoursebyFaculty() {
//        return coursebyFaculty;
//    }
//
//    public void setCoursebyFaculty(Map<Courses, Faculty> coursebyFaculty) {
//        this.coursebyFaculty = coursebyFaculty;
//    }
}