package com.example.cmpe202project.repository;

import com.example.cmpe202project.model.Faculty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends MongoRepository<Faculty, Integer> {
    List<Faculty> findByFirstName(String firstName);
    List<Faculty> findByLastName(String lastName);
    List<Faculty> findByFirstNameAndLastName(String firstName, String lastName);
    Faculty findByEmail(String email);
}