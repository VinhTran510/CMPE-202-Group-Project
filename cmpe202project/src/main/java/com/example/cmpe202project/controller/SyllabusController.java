package com.example.cmpe202project.controller;


import com.example.cmpe202project.model.Syllabus;
import com.example.cmpe202project.dto.SyllabusDTO;
import com.example.cmpe202project.service.SyllabusService;
import com.example.cmpe202project.repository.SyllabusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/syllabus")
public class SyllabusController {
    private final SyllabusService syllabusService;

    @Autowired
    public SyllabusController(SyllabusService syllabusService) {
        this.syllabusService = syllabusService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Syllabus> updateSyllabus(
            @PathVariable String id,
            @RequestBody SyllabusDTO syllabusDTO) {
        Syllabus updatedSyllabus = syllabusService.updateSyllabusContent(id, syllabusDTO.getSyllabusContent());
        return ResponseEntity.ok(updatedSyllabus);
    }
    @PostMapping("/")
    public ResponseEntity<Syllabus> createSyllabus(@RequestBody SyllabusDTO syllabusDTO) {
        Syllabus newSyllabus = new Syllabus();
        // You would set other properties from the DTO to the Syllabus model here
        newSyllabus.setFirstname(syllabusDTO.getFirstname());
        newSyllabus.setLastname(syllabusDTO.getLastname());
        newSyllabus.setTitle(syllabusDTO.getTitle());
        newSyllabus.setCode(syllabusDTO.getCode());
        newSyllabus.setSemester(syllabusDTO.getSemester());
        newSyllabus.setSyllabusContent(syllabusDTO.getSyllabusContent());
        newSyllabus.setOfficeLocation(syllabusDTO.getOfficeLocation());
        newSyllabus.setOfficeHours(syllabusDTO.getOfficeHours());
        newSyllabus.setClassDaysTime(syllabusDTO.getClassDaysTime());
        newSyllabus.setClassroom(syllabusDTO.getClassroom());


        Syllabus createdSyllabus = syllabusService.createSyllabus(newSyllabus);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSyllabus);
    }

}
