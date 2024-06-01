package com.example.cmpe202project.controller;

import ch.qos.logback.core.model.Model;
import com.example.cmpe202project.exception.ResourceNotFoundException;
import com.example.cmpe202project.model.Courses;
import com.example.cmpe202project.model.Faculty;
import com.example.cmpe202project.model.Student;
import com.example.cmpe202project.service.CoursesService;
import com.example.cmpe202project.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import java.util.List;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "http://localhost:63342") // Allow CORS requests from this origin
public class CoursesController {

    private final CoursesService coursesService;
    private final FacultyService facultyService;

    public CoursesController(CoursesService coursesService, FacultyService facultyService) {
        this.coursesService = coursesService;
        this.facultyService = facultyService;
    }

    //@PostMapping
    public ResponseEntity<Courses> create(@RequestBody Courses courses) {
        if (StringUtils.isEmpty(courses.getCourseName()) || courses.getCourseName().length() < 2 || courses.getCourseName().length() > 30) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(coursesService.save(courses));
    }

    @GetMapping
    public List<Courses> findAll() {
        return coursesService.findAll();
    }

    @GetMapping("/{id}")
    public Courses findById(@PathVariable Integer id) {
        return coursesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        coursesService.deleteById(id);
    }

    @GetMapping("/{id}/students")
    public List<Integer> getStudentList(@PathVariable Integer id) {
        return coursesService.getStudentList(id); // Use coursesRepository instance here
    }

    @PostMapping("/validate")
    public ResponseEntity<?> create(@Valid @RequestBody Courses courses, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(coursesService.save(courses));
    }


    @GetMapping("/{courseId}/studentNames")
    public ResponseEntity<List<String>> getStudentsByCourse(@PathVariable int courseId) {
        List<String> students = coursesService.getStudentNamesByCourse(courseId);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{courseId}/assignments")
    public List<Integer> getAssignmentsByCourse(@PathVariable int courseId) {
        return coursesService.getAssignments(courseId);
    }


    //Create a new course
    @PostMapping("/create")
    public ResponseEntity<Courses> createCourse(@RequestBody Courses course) {
        // Validate the course object
        if (course.getCourseName() == null || course.getCourseName().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Save the course object using the CoursesService
        Courses savedCourse = coursesService.createCourse(course);

        // Return the saved course object
        return ResponseEntity.ok(savedCourse);
    }

    @GetMapping("/findByFacultyIDAndSemester")
    public List<Courses> findByFacultyIDAndSemester(@RequestParam Integer facultyID, @RequestParam(required = false)  String semester) {
        return coursesService.findByFacultyIDAndSemester(facultyID, semester);
    }

    @GetMapping("/filter")
    public List<Courses> filterCourses(@RequestParam Integer facultyID,
                                       @RequestParam(required = false) String semester) {
        return coursesService.filterCourses(facultyID, semester);
    }

    @GetMapping("/findByFaculty")
    public List<Courses> findByFacultyID(@PathVariable Integer facultyID) {
        return coursesService.findByFacultyID(facultyID);
    }
        @GetMapping("/{id}/grades")
    public Map<String, Integer> getGrades(@PathVariable Integer id) {
        return coursesService.getGrades(id);
    }


}

