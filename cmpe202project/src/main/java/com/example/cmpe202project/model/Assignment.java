package com.example.cmpe202project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotEmpty;

import java.util.Map;

@Document(collection = "assignments")
public class Assignment {

    @Id
    private int  id;

    @NotEmpty(message = "Title cannot be empty")
    private String title;
    private int courseID;
    private boolean isQuiz;
    private boolean isPublished;
    private Map<String,Integer > work;

    public Assignment() {

    }

    public Assignment(int id, String title, boolean isQuiz, boolean isPublished, Map<String, Integer> work) {

        this.id = id;
        this.title = title;
        this.isQuiz = isQuiz;
        this.isPublished = isPublished;
        this.work = work;
    }


    public int getId() {
        return id;
    }

    public void setId(int  id) {
        this.id = id;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int  id) {
        this.courseID = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isQuiz() {
        return isQuiz;
    }

    public void setQuiz(boolean quiz) {
        isQuiz = quiz;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public Map<String, Integer> getWork() {
        return work;
    }


    public void setWork(Map<String, Integer> work) {
        this.work = work;
    }

    public void setSingleWork(int s, int g) {
        work.put(Integer.toString(s), g);
    }

}
