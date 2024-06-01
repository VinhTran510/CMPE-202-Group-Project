package com.example.cmpe202project.service;

import com.example.cmpe202project.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseConnectionService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public DatabaseConnectionService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Student> checkConnection() {
        return mongoTemplate.findAll(Student.class);
    }
}
