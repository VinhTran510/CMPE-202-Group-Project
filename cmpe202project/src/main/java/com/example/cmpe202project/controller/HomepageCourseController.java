package com.example.cmpe202project.controller;

import com.example.cmpe202project.model.HomepageCourse;
import com.example.cmpe202project.service.HomepageCourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/homepageCourse")
public class HomepageCourseController {
    private final HomepageCourseService homepageCourseService;

    public HomepageCourseController(HomepageCourseService homepageCourseService) {
        this.homepageCourseService = homepageCourseService;
    }

    @GetMapping
    public List<HomepageCourse> getAllHomepageCourses() {
        return homepageCourseService.getAllHomepageCourses();
    }

    @GetMapping("/current")
    public List<HomepageCourse> getCurrentHomepageCourses() {
        return homepageCourseService.getCurrentHomepageCourses();
    }

    @GetMapping("/previous")
    public List<HomepageCourse> getPreviousHomepageCourses() {
        return homepageCourseService.getPreviousHomepageCourses();
    }

    // New endpoint for future courses
    @GetMapping("/future")
    public List<HomepageCourse> getFutureHomepageCourses() {
        return homepageCourseService.getFutureHomepageCourses();
    }
}
