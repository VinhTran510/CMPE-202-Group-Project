package com.example.cmpe202project.service;

import com.example.cmpe202project.model.Courses;
import com.example.cmpe202project.model.Student;
import com.example.cmpe202project.repository.CoursesRepository;
import com.example.cmpe202project.repository.FacultyRepository;
import com.example.cmpe202project.repository.StudentRepository;
import org.springframework.stereotype.Service;
import com.example.cmpe202project.exception.ResourceNotFoundException;


import java.util.*;

@Service
public class CoursesService {

    private final CoursesRepository coursesRepository;
    private final StudentRepository studentRepository;
    private FacultyRepository facultyRepository;

    public CoursesService(CoursesRepository coursesRepository, StudentRepository studentRepository) {
        this.coursesRepository = coursesRepository;
        this.studentRepository = studentRepository;
    }

    public Courses save(Courses courses) {
        return coursesRepository.save(courses);
    }

    public List<Courses> findAll() {
        return coursesRepository.findAll();
    }

    public Optional<Courses> findById(Integer id) {
        return coursesRepository.findById(id);
    }

    public List<Courses> findByFacultyID(Integer id) {
        return coursesRepository.findByFacultyID(id);
    }

    public List<Courses> findByFacultyIDAndSemester(Integer id, String Semester) {
        return coursesRepository.findByFacultyIDAndSemester(id, Semester);
    }

    public void deleteById(Integer id) {
        coursesRepository.deleteById(id);
    }

    public List<Courses> filterCourses(Integer facultyID, String semester) {
        if (facultyID != null && semester != null && !semester.isEmpty()) {
            // If both faculty id and semester provided, filter by both
            return coursesRepository.findByFacultyIDAndSemester(facultyID, semester);
        } else if (facultyID != null) {
            // If only facultyId is provided, filter by facultyId
            return coursesRepository.findByFacultyID(facultyID);
        } else {
            // If neither first name nor last name is provided, return all faculties
            return findAll();
        }
    }

    public Courses update(Integer id, Courses updatedCourse) {
        return coursesRepository.findById(id)
                .map(courses -> {
                    courses.setCourseName(updatedCourse.getCourseName());
                    // update other fields as needed
                    return coursesRepository.save(courses);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
    }

    public List<String> getStudentNamesByCourse(int courseId) {
        Courses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        List<String> studentNames = new ArrayList<>();
        for (Integer i : course.getStudents()) {
            Optional<Student> s = studentRepository.findById(i);
            String fullName = s.get().getFirstName() + " " + s.get().getLastName();
            studentNames.add(fullName);
        }
        return studentNames;
    }

    public List<Integer> getCourseStudents(Integer courseId) {
        Courses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return new ArrayList<>(course.getStudents());
    }

    public List<Courses> findByCourseName(String courseName) {
        return coursesRepository.findByCourseName(courseName);
    }

    public List<Courses> findBySemester(String semester) {
        return coursesRepository.findBySemester(semester);
    }

    public Map<String, Integer> getGrades(Integer id) {
        return coursesRepository.findById(id).get().getGrades();
    }

    public void setGrades(Integer id, Map<String, Integer> grades) {
        Courses course = coursesRepository.findById(id).get();
        course.setGrades(grades);
        coursesRepository.save(course);
    }

    public List<Integer> getStudentList(Integer id) {
        return coursesRepository.findById(id).get().getStudents();
    }


    //Create a new course
    public Courses createCourse(Courses course) {
        // Generate a unique ID for the course
        int newId = generateUniqueId();
        course.setId(newId);

        // Save the course to the database
        return coursesRepository.save(course);
    }

    private int generateUniqueId() {
        // Find the highest current ID
        OptionalInt maxId = coursesRepository.findAll().stream()
                .mapToInt(Courses::getId)
                .max();

        // If no IDs exist yet, start with 1, otherwise increment the max ID
        return maxId.isPresent() ? maxId.getAsInt() + 1 : 1;
    }

    public List<Integer> getAssignments(Integer courseId) {
        Courses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        List<Integer> assignments = new ArrayList<>();
        assignments = course.getAssignmentList();
        return assignments;
    }

    public void addAssignment(Integer courseId, Integer assignmentId) {
        Courses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));;
        course.addAssignment(assignmentId);
        coursesRepository.save(course);
    }


}