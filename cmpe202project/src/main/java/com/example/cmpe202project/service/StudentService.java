package com.example.cmpe202project.service;

import com.example.cmpe202project.model.Student;
import com.example.cmpe202project.model.Courses;

import com.example.cmpe202project.repository.StudentRepository;
import org.springframework.data.mongodb.core.aggregation.VariableOperators;
import org.springframework.stereotype.Service;
import com.example.cmpe202project.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Integer id) {
        return studentRepository.findById(id);
    }

    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }
    public Student update(Integer id, Student updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setFirstName(updatedStudent.getFirstName());
                    student.setLastName(updatedStudent.getLastName());
                    student.setEmail(updatedStudent.getEmail());
                    // update other fields as needed
                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }
    public List<Student> findByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    public List<Student> findByLastName(String lastName) {
        return studentRepository.findByLastName(lastName);
    }

    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Student setCourses(Integer id, List<Integer> courses) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setCourseList(courses);
                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    public List<Integer> getCourses(Integer id) {
        return studentRepository.findById(id).get().getCourseList();
    }

    public Map<String, Integer> getAssignments(Integer id) {
        return studentRepository.findById(id).get().getAssignments();
    }
    public Map<String, Boolean> getNotifications(Integer id) {
        return studentRepository.findById(id).get().getNotifications();
    }
    public void setNotifications(Integer id, Map<String, Boolean> notifications) {
        studentRepository.findById(id)
                .map(student -> {
                    student.setNotifications(notifications);
                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

}