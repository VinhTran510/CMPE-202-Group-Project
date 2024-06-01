package com.example.cmpe202project.repository;

import com.example.cmpe202project.model.Courses;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface CoursesRepository extends MongoRepository<Courses, Integer> {
    List<Courses> findByCourseName(String courseName);
    List<Courses> findBySemester(String semester);
    List<Courses> findAllById(List<Integer> courseIds);
    List<Courses> findByFacultyID(Integer id);
    List<Courses> findByFacultyIDAndSemester(Integer id, String semester);
}