package com.example.cmpe202project.repository;

import com.example.cmpe202project.model.Assignment;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface AssignmentRepository extends MongoRepository<Assignment, Integer> {

    List<Assignment> findByTitle(String title);
    List<Assignment> findByIsQuiz(boolean isQuiz);
    List<Assignment> findByIsPublished(boolean isPublished);
}