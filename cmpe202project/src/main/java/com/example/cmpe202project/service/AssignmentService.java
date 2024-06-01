package com.example.cmpe202project.service;

import com.example.cmpe202project.model.Assignment;
import com.example.cmpe202project.repository.AssignmentRepository;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public Assignment createAssignment(String title, Boolean publish, Boolean isQuiz, Boolean isPublished, Map<String, Integer> work) {
        Assignment assignment = new Assignment();
        assignment.setTitle(title);
        assignment.setPublished(publish);
        assignment.setQuiz(isQuiz);
        assignment.setPublished(isPublished);
        assignment.setWork(work);
        return assignmentRepository.save(assignment);
    }


    public Assignment save(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> findAll() {
        return assignmentRepository.findAll();
    }

    public Optional<Assignment> findById(Integer id) {
        return assignmentRepository.findById(id);
    }

    public void deleteById(Integer id) {
        assignmentRepository.deleteById(id);
    }

    public List<Assignment> findByTitle(String title) {
        return assignmentRepository.findByTitle(title);
    }

    public List<Assignment> findByIsQuiz(boolean isQuiz) {
        return assignmentRepository.findByIsQuiz(isQuiz);
    }

    public List<Assignment> findByIsPublished(boolean isPublished) {
        return assignmentRepository.findByIsPublished(isPublished);
    }

    public Map<String, Integer> getWork(Integer id) {
        return assignmentRepository.findById(id).get().getWork();
    }

    public void setWork(Integer id, Map<String, Integer> work) {
        Assignment assignment = assignmentRepository.findById(id).get();
        assignment.setWork(work);
        assignmentRepository.save(assignment);
    }

    public void setSingleWork(Integer id, Integer studentId, Integer grade) {
        Assignment assignment = assignmentRepository.findById(id).get();
        assignment.setSingleWork(studentId, grade);
        assignmentRepository.save(assignment);
    }


    public List<Assignment> getAll(Integer id) {
        return assignmentRepository.findAll();
    }

}