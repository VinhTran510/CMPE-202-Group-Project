package com.example.cmpe202project.service;
import com.example.cmpe202project.model.Syllabus;
import com.example.cmpe202project.repository.SyllabusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SyllabusService {
    private final SyllabusRepository syllabusRepository;

    @Autowired
    public SyllabusService(SyllabusRepository syllabusRepository) {
        this.syllabusRepository = syllabusRepository;
    }

    public Syllabus updateSyllabusContent(String id, String content) {
        Optional<Syllabus> syllabusOptional = syllabusRepository.findById(id);
        if (!syllabusOptional.isPresent()) {
            throw new RuntimeException("Syllabus not found with id: " + id);
        }
        Syllabus syllabus = syllabusOptional.get();
        syllabus.setSyllabusContent(content); // Update the content
        return syllabusRepository.save(syllabus); // Save the updated syllabus
    }
    public Syllabus createSyllabus(Syllabus syllabus) {
        return syllabusRepository.save(syllabus); // Correct usage of instance method
    }

}
