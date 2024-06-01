package com.example.cmpe202project.repository;

import com.example.cmpe202project.model.CourseManager;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseManagerRepository extends MongoRepository<CourseManager, Integer> {
    List<CourseManager> findBySemesterIDAndIsCurrent(int semesterID, boolean isCurrent);
    List<CourseManager> findByPublished(boolean published);
}
