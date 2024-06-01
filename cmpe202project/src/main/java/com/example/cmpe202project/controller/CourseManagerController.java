package com.example.cmpe202project.controller;

import com.example.cmpe202project.model.CourseManager;
import com.example.cmpe202project.service.CourseManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseManagerController {

    @Autowired
    private CourseManagerService courseManagerService;

    @GetMapping("/current")
    public List<CourseManager> getCurrentCourses() {
        return courseManagerService.getCurrentCourses();
    }

    @GetMapping("/published")
    public List<CourseManager> getPublishedCourses() {
        return courseManagerService.getPublishedCourses();
    }

    @GetMapping("/unpublished")
    public List<CourseManager> getUnpublishedCourses() {
        return courseManagerService.getUnpublishedCourses();
    }

    @PutMapping("/publish/{id}")
    public CourseManager publishCourse(@PathVariable int id) {
        return courseManagerService.publishCourse(id);
    }

    @PutMapping("/unpublish/{id}")
    public CourseManager unpublishCourse(@PathVariable int id) {
        return courseManagerService.unpublishCourse(id);
    }
}
