package com.example.cmpe202project.controller;

import com.example.cmpe202project.model.Faculty;
import com.example.cmpe202project.model.Student;
import com.example.cmpe202project.service.CoursesService;
import com.example.cmpe202project.service.FacultyService;

import com.example.cmpe202project.service.StudentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/faculty")
public class FacultyController {

    private final CoursesService c;

    private final FacultyService facultyService;

    public FacultyController(CoursesService c, FacultyService facultyService) {
        this.c = c;
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty create(@RequestBody Faculty faculty) {
        return facultyService.save(faculty);
    }

    @GetMapping
    public List<Faculty> findAll() {
        return facultyService.findAll();
    }



    @GetMapping("/{id}")
    public Faculty findById(@PathVariable Integer id) {
        return facultyService.findById(id).orElse(null);
    }

    @GetMapping("/findByFullName")
    public List<Faculty> findByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        return facultyService.findByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getFacultyByEmail(@PathVariable String email) {
        Faculty faculty = facultyService.findFacultyByEmail(email);
        if (faculty != null) {
            return ResponseEntity.ok(faculty);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty not found with email: " + email);
        }
    }


    @GetMapping("/findByFN")
    public List<Faculty> findByFirstName(@PathVariable String fistName) {
        return facultyService.findByFirstName(fistName);
    }

    @GetMapping("/findByLN")
    public List<Faculty> findByLastName(@PathVariable String lastName) {
        return facultyService.findByLastName(lastName);
    }

    @GetMapping("/filter")
    public List<Faculty> filterFaculties(@RequestParam(required = false) String firstName,
                                         @RequestParam(required = false) String lastName) {
        return facultyService.filterFaculties(firstName, lastName);
    }

}