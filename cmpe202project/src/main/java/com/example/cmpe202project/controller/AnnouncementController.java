package com.example.cmpe202project.controller;

import com.example.cmpe202project.model.Announcement;
import com.example.cmpe202project.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createAnnouncement(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("postTo") String postTo,
            @RequestParam(value = "attachment", required = false) MultipartFile attachment) {
        try {
            Announcement announcement = announcementService.createAnnouncement(title, content, postTo, attachment);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(announcement.getId())
                    .toUri();
            return ResponseEntity.created(location).body(announcement);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to save file: " + e.getMessage());
        }
    }
}
