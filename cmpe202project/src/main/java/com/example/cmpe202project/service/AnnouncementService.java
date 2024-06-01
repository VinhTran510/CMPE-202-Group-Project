package com.example.cmpe202project.service;

import com.example.cmpe202project.model.Announcement;
import com.example.cmpe202project.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    @Autowired
    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public Announcement createAnnouncement(String title, String content, String postTo, MultipartFile attachment) throws IOException {
        Announcement announcement = new Announcement();
        announcement.setTitle(title);
        announcement.setContent(content);
        announcement.setPostTo(postTo);
        announcement.setPostedDate(new Date());

        if (attachment != null && !attachment.isEmpty()) {
            String directory = "/Users/gloriadjonret/Documents/demo1/uploads/";  // Define the directory path for uploads
            Path filepath = Paths.get(directory, attachment.getOriginalFilename());
            Files.createDirectories(filepath.getParent());  // Ensure directory exists
            Files.copy(attachment.getInputStream(), filepath);
            announcement.setAttachmentPath(filepath.toString());
        }

        return announcementRepository.save(announcement);
    }
}
