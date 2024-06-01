package com.example.cmpe202project.repository;

import com.example.cmpe202project.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface StudentRepository extends MongoRepository<Student, Integer> {
    List<Student> findByFirstName(String firstName);
    List<Student> findByLastName(String lastName);
    Student findByEmail(String email);
}