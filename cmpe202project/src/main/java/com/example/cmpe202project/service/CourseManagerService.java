package com.example.cmpe202project.service;

import com.example.cmpe202project.model.CourseManager;
import com.example.cmpe202project.repository.CourseManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CourseManagerService {

    @Autowired
    private CourseManagerRepository courseManagerRepository;

    public List<CourseManager> getCurrentCourses() {
        return courseManagerRepository.findBySemesterIDAndIsCurrent(4, true);
    }

    public List<CourseManager> getPublishedCourses() {
        return courseManagerRepository.findByPublished(true);
    }

    public List<CourseManager> getUnpublishedCourses() {
        return courseManagerRepository.findByPublished(false);
    }

    public CourseManager publishCourse(int courseId) {
        CourseManager course = courseManagerRepository.findById(courseId).orElse(null);
        if (course != null) {
            course.setPublished(true);
            return courseManagerRepository.save(course);
        }
        return null; // Course not found
    }

    public CourseManager unpublishCourse(int courseId) {
        CourseManager course = courseManagerRepository.findById(courseId).orElse(null);
        if (course != null) {
            course.setPublished(false);
            return courseManagerRepository.save(course);
        }
        return null; // Course not found
    }
}
