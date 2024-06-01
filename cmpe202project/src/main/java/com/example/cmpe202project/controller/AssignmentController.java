package com.example.cmpe202project.controller;

import com.example.cmpe202project.model.Assignment;
import com.example.cmpe202project.model.Courses;
import com.example.cmpe202project.model.Student;
import com.example.cmpe202project.service.AssignmentService;
import com.example.cmpe202project.service.CoursesService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/assignments")
@CrossOrigin(origins = "http://localhost:63342") // Allow CORS requests from this origin

public class AssignmentController {

    private final AssignmentService assignmentService;
    private final CoursesService coursesService;

    public AssignmentController(AssignmentService assignmentService, CoursesService coursesService) {
        this.assignmentService = assignmentService;
        this.coursesService = coursesService;
    }

    @PostMapping("/newassignment")
    public ResponseEntity<Assignment> create(@RequestBody Assignment assignment) {
        if (StringUtils.isEmpty(assignment.getTitle()) || assignment.getTitle().length() < 2 || assignment.getTitle().length() > 50) {
            return ResponseEntity.badRequest().body(null);
        }

        // Add similar checks for other fields...
        return ResponseEntity.ok(assignmentService.save(assignment));
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void create(String title, Integer isQuiz, Integer courseId, HttpServletResponse response) throws IOException {
        Assignment assignment = new Assignment();
        int currId = assignmentService.findAll().size() + 1;
        assignment.setTitle(title);
        assignment.setQuiz(isQuiz != null);
        assignment.setId(currId);
        assignment.setCourseID(courseId);
        HashMap<String, Integer> work = new HashMap<String,Integer>();
        for(int s : coursesService.getCourseStudents(courseId)){
            work.put(Integer.toString(s), 0);
        }
        assignment.setWork(work);
        // Add similar checks for other fields...
        coursesService.addAssignment(courseId, currId);
        assignmentService.save(assignment);
        response.sendRedirect("/assignments.html?id=" + courseId);
    }

    @GetMapping
    public List<Assignment> findAll() {
        return assignmentService.findAll();
    }

    @GetMapping("/{id}/work")
    public Map<String, Integer> getWork(@PathVariable Integer id) {
        return assignmentService.getWork(id);
    }

    @PutMapping("/{id}/work")
    public void setWork(@PathVariable Integer id, @RequestBody Map<String, Integer> work) {
        assignmentService.setWork(id, work);
    }

    @GetMapping("/{id}")
    public Assignment findById(@PathVariable Integer id) {
        return assignmentService.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        assignmentService.deleteById(id);
    }

    @PostMapping("/validate")
    public ResponseEntity<?> create(@Valid @RequestBody Assignment assignment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(assignmentService.save(assignment));
    }

    @RequestMapping(value = "/setGrade", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void setWork(Integer assignmentId, Integer studentId, Integer grade, HttpServletResponse response) throws IOException {
       assignmentService.setSingleWork(assignmentId,studentId,grade);
       response.sendRedirect("/assignment.html?id=" + assignmentId);
    }


}

