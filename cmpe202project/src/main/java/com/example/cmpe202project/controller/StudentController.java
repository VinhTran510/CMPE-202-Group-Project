package com.example.cmpe202project.controller;

import com.example.cmpe202project.exception.ResourceNotFoundException;
import com.example.cmpe202project.model.Student;
import com.example.cmpe202project.model.Courses;
import com.example.cmpe202project.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;
import  com.example.cmpe202project.repository.CoursesRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:63342") // Allow CORS requests from this origin

public class StudentController {

    private final StudentService studentService;
    
    public StudentController(StudentService studentService, CoursesRepository coursesRepository) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        if (StringUtils.isEmpty(student.getFirstName()) || student.getFirstName().length() < 2 || student.getFirstName().length() > 50) {
            return ResponseEntity.badRequest().body(null);
        }

        if (StringUtils.isEmpty(student.getLastName()) || student.getLastName().length() < 2 || student.getLastName().length() > 50) {
            return ResponseEntity.badRequest().body(null);
        }

        if (StringUtils.isEmpty(student.getEmail()) || !student.getEmail().contains("@")) {
            return ResponseEntity.badRequest().body(null);
        }

        if (StringUtils.isEmpty(student.getPassword()) || student.getPassword().length() < 8) {
            return ResponseEntity.badRequest().body(null);
        }

        // Add checks for Student-specific fields...

        return ResponseEntity.ok(studentService.save(student));
    }

    @GetMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable Integer id) {
        return studentService.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        studentService.deleteById(id);
    }

    @GetMapping("/{id}/courses")
    public List<Integer> getStudentCourses(@PathVariable Integer id) {

        return studentService.getCourses(id); // Use coursesRepository instance here
    }

    @PutMapping("/{id}/courses")
    public Student setStudentCourses(@PathVariable Integer id, @RequestBody List<Integer> courses) {
        return studentService.setCourses(id, courses);
    }

    @GetMapping("/{id}/assignments")
    public Map<String, Integer> getAssignments(@PathVariable Integer id) {
        return studentService.getAssignments(id);
    }

    @PutMapping("/{id}/notifications")
    public Student setNotifications(@PathVariable Integer id, @RequestBody Map<String, Boolean> notifications) {
        studentService.setNotifications(id, notifications);
        return studentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }



    @GetMapping("/{id}/notifications")
    public Map<String, Boolean> getNotifications(@PathVariable Integer id) {
        return studentService.getNotifications(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student updatedStudent) {
        return studentService.findById(id)
                .map(student -> {
                    student.setFirstName(updatedStudent.getFirstName());
                    student.setEmail(updatedStudent.getEmail());
                    student.setPassword(updatedStudent.getPassword());
                    return ResponseEntity.ok(studentService.save(student));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }



}