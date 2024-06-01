package com.example.cmpe202project.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "syllabus") // Note that 'syllabus' is singular, but MongoDB collections are typically plural
public class Syllabus {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String title;
    private String code; // e.g., "CS101"
    private String semester; // e.g., "Fall 2024"

    private String syllabusContent; // Assuming 'syllabus' field in JSON is the content
    private String officeLocation;
    private String officeHours;
    private String classDaysTime;
    private String classroom;


    // Default constructor (for Spring Data)
    public Syllabus() {
    }

    // Parametrized constructor for convenience
    public Syllabus(String id, String firstname, String lastname, String title, String code, String semester, String syllabusContent, String officeLocation, String officeHours, String classDaysTime, String classroom) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.title = title;
        this.code = code;
        this.semester = semester;
        this.syllabusContent = syllabusContent;
        this.officeLocation = officeLocation;

        this.officeHours = officeHours;
        this.classDaysTime = classDaysTime;
        this.classroom = classroom;

    }

    // Getters and setters
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }



    public String getSyllabusContent() {
        return syllabusContent;
    }

    public void setSyllabusContent(String syllabusContent) {
        this.syllabusContent = syllabusContent;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }



    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public String getClassDaysTime() {
        return classDaysTime;
    }

    public void setClassDaysTime(String classDaysTime) {
        this.classDaysTime = classDaysTime;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }



    // toString method for logging and debugging purposes
    @Override
    public String toString() {
        return "Syllabus{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", semester='" + semester + '\'' +
                ", syllabusContent='" + syllabusContent + '\'' +
                ", officeLocation='" + officeLocation + '\'' +
                ", officeHours='" + officeHours + '\'' +
                ", classDaysTime='" + classDaysTime + '\'' +
                ", classroom='" + classroom + '\'' +
                '}';
    }
}
