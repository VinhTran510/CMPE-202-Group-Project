package com.example.cmpe202project.repository;

import com.example.cmpe202project.model.Announcement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnnouncementRepository extends MongoRepository<Announcement, String> {
    // Existing query methods
}
