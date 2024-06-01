package com.example.cmpe202project.service;

import com.example.cmpe202project.model.HomepageCourse;
import com.example.cmpe202project.repository.HomepageCourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomepageCourseService {
    private final HomepageCourseRepository homepageCourseRepository;

    public HomepageCourseService(HomepageCourseRepository homepageCourseRepository) {
        this.homepageCourseRepository = homepageCourseRepository;
    }

    public List<HomepageCourse> getAllHomepageCourses() {
        return homepageCourseRepository.findAll();
    }

    public List<HomepageCourse> getCurrentHomepageCourses() {
        return homepageCourseRepository.findAll().stream()
                .filter(homepageCourse -> homepageCourse.getSemesterID() == 4)
                .collect(Collectors.toList());
    }

    public List<HomepageCourse> getPreviousHomepageCourses() {
        return homepageCourseRepository.findAll().stream()
                .filter(homepageCourse -> homepageCourse.getSemesterID() < 4)
                .collect(Collectors.toList());
    }

    // New method to fetch future courses
    public List<HomepageCourse> getFutureHomepageCourses() {
        return homepageCourseRepository.findAll().stream()
                .filter(homepageCourse -> homepageCourse.getSemesterID() > 4)
                .collect(Collectors.toList());
    }
}
