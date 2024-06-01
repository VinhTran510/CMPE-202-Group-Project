package com.example.cmpe202project.service;

import com.example.cmpe202project.model.Faculty;
import com.example.cmpe202project.repository.FacultyRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public List<Faculty> findAll() {
        return facultyRepository.findAll();
    }


    public Faculty save(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

//    public Optional<Faculty> findById(Integer id) {
//        return facultyRepository.findById(id);
//    }

    public List<Faculty> findByFirstName(String firstName) {
        return facultyRepository.findByFirstName(firstName);
    }
    public Optional<Faculty> findById(Integer id) {
        return facultyRepository.findById(id);
    }

    public List<Faculty> findByLastName(String lastName) {
        return facultyRepository.findByLastName(lastName);
    }

    public List<Faculty> findByFirstNameAndLastName(String firstName, String lastName) {
        return facultyRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public Faculty findFacultyByEmail(String email) {
        return facultyRepository.findByEmail(email);
    }

    public Faculty findFacultyById(Integer id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public List<Faculty> filterFaculties(String firstName, String lastName) {
        if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()) {
            // If both first name and last name are provided, filter by both
            return facultyRepository.findByFirstNameAndLastName(firstName, lastName);
        } else if (firstName != null && !firstName.isEmpty()) {
            // If only first name is provided, filter by first name
            return facultyRepository.findByFirstName(firstName);
        } else if (lastName != null && !lastName.isEmpty()) {
            // If only last name is provided, filter by last name
            return facultyRepository.findByLastName(lastName);
        } else {
            // If neither first name nor last name is provided, return all faculties
            return findAll();
        }
    }
}