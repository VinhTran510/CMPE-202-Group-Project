package com.example.cmpe202project.repository;

import com.example.cmpe202project.model.Syllabus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SyllabusRepository extends MongoRepository<Syllabus, String> {
    // Existing query methods
}
