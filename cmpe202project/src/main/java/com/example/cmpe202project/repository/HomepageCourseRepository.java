package com.example.cmpe202project.repository;

import com.example.cmpe202project.model.HomepageCourse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HomepageCourseRepository extends MongoRepository<HomepageCourse, String> {
}


