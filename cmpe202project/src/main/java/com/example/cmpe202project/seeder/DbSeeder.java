/*package com.example.cmpe202project.seeder;

import com.example.cmpe202project.model.Student;
import com.example.cmpe202project.model.Courses;
import com.example.cmpe202project.model.Assignment;
import com.example.cmpe202project.repository.StudentRepository;
import com.example.cmpe202project.repository.CoursesRepository;
import com.example.cmpe202project.repository.AssignmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.dao.DataAccessException;


import java.util.Arrays;
import java.util.HashMap;

@Component
public class DbSeeder implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbSeeder.class);

    private final StudentRepository studentRepository;
    private final CoursesRepository coursesRepository;
    private final AssignmentRepository assignmentRepository;

    public DbSeeder(StudentRepository studentRepository, CoursesRepository coursesRepository, AssignmentRepository assignmentRepository) {
        this.studentRepository = studentRepository;
        this.coursesRepository = coursesRepository;
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Delete all existing entities
        this.studentRepository.deleteAll();
        this.coursesRepository.deleteAll();
        this.assignmentRepository.deleteAll();

        // Create new Assignment entities
        Assignment assignment1 = new Assignment(1, "Assignment 1", false, false, new HashMap<>());
        Assignment assignment2 = new Assignment(2, "Assignment 2", false, false, new HashMap<>());
        Assignment assignment3 = new Assignment(3, "Assignment 3", false, false, new HashMap<>());

        Courses course1 = new Courses(1, "Course 1", "Semester 1", 1, "Faculty 1", null, Arrays.asList(assignment1, assignment2));
        Courses course2 = new Courses(2, "Course 2", "Semester 1", 1, "Faculty 1", null, Arrays.asList(assignment1, assignment2));
        Courses course3 = new Courses(3, "Course 3", "Semester 1", 1, "Faculty 1", null, Arrays.asList(assignment1, assignment2));

        Student student1 = new Student(1, "John", "Doe", "john.doe@example.com", "password123", Arrays.asList(course1, course2), null, new HashMap<>());
        Student student2 = new Student(2, "Jane", "Doe", "jane.doe@example.com", "password123", Arrays.asList(course1, course2), null, new HashMap<>());
        Student student3 = new Student(3, "Alice", "Smith", "alice.smith@example.com", "password123", Arrays.asList(course1, course2), null, new HashMap<>());

        try {
            // Save Assignment entities to the database
            Assignment savedAssignment1 = this.assignmentRepository.save(assignment1);
            Assignment savedAssignment2 = this.assignmentRepository.save(assignment2);
            Assignment savedAssignment3 = this.assignmentRepository.save(assignment3);
            LOGGER.info("Saved assignments: {}, {}, {}", savedAssignment1, savedAssignment2, savedAssignment3);

            // Save Courses entities to the database
            Courses savedCourse1 = this.coursesRepository.save(course1);
            Courses savedCourse2 = this.coursesRepository.save(course2);
            Courses savedCourse3 = this.coursesRepository.save(course3);
            LOGGER.info("Saved courses: {}, {}, {}", savedCourse1, savedCourse2, savedCourse3);

            // Save Student entities to the database
            Student savedStudent1 = this.studentRepository.save(student1);
            Student savedStudent2 = this.studentRepository.save(student2);
            Student savedStudent3 = this.studentRepository.save(student3);
            LOGGER.info("Saved students: {}, {}, {}", savedStudent1, savedStudent2, savedStudent3);
        } catch (DataAccessException e) {
            LOGGER.error("Error saving data to the database", e);
        }

        LOGGER.info("Database has been initialized");
    }
}*/