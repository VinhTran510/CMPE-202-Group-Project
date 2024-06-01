package com.example.cmpe202project.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Document(collection = "students")
public class Student extends User {

    private List<Integer> courses;
    private List<Boolean> profileInfo;
    private Map<String, Integer> assignments;
    private Map<String,Boolean> notifications;


    public Student() {
            this.courses = new ArrayList<>();

    }


    public Student(int id, String firstName, String lastName, String email, String password, List<Courses> courseList, List<Boolean> profileInfo, Map<String, Integer> assignments, Map<String,Boolean> notifications) {
        super(id, firstName, lastName, email, password);
        this.courses = new ArrayList<>();
        this.profileInfo = profileInfo;
        this.assignments = assignments;

        if (notifications == null || notifications.isEmpty()) {
            this.notifications = new HashMap<>();
            this.notifications.put("sms", true);
            this.notifications.put("email", true);
            this.notifications.put("push notification", true);
        } else {
            this.notifications = notifications;
        }
    }

    public List<Integer> getCourseList() {
        return courses;
    }


    public void setCourseList(List<Integer> courseList) {
            if (courseList == null) {
                this.courses = new ArrayList<>();
            } else {
                this.courses = courseList;
            }
        }

    public List<Boolean> getProfileInfo() {
        return profileInfo;
    }

    public void setProfileInfo(List<Boolean> profileInfo) {
        this.profileInfo = profileInfo;
    }

    public Map<String, Integer> getAssignments() {
        return assignments;
    }

    public Map<String, Boolean> getNotifications() {
        return notifications;
    }

    public void setNotifications(Map<String, Boolean> notifications) {
        this.notifications = notifications;
    }
}