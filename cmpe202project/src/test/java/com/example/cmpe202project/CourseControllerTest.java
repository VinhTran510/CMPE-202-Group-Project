package com.example.cmpe202project;

import com.example.cmpe202project.controller.CoursesController;
import com.example.cmpe202project.model.Courses;
import com.example.cmpe202project.model.Student;
import com.example.cmpe202project.model.Assignment;
import com.example.cmpe202project.service.CoursesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/*
@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoursesService coursesService;

    @Test
    public void testCreateCourses() throws Exception {
        List<Student> studentList = new ArrayList<>();
        List<Integer> assignmentList = new ArrayList<>();
        Map<String, Integer> grades = new HashMap<>();

        Courses courses = new Courses(1, "Test Course", "Spring", "2024",1 "faculty1", studentList, assignmentList,grades);
        when(coursesService.save(courses)).thenReturn(courses);

        mockMvc.perform(MockMvcRequestBuilders.post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"courseName\":\"Test Course\",\"semester\":\"Spring\",\"semesterID\":2022,\"facultyID\":\"faculty1\",\"studentList\":[],\"assignmentList\":[]}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindCoursesById() throws Exception {
        List<Student> studentList = new ArrayList<>();
        List<Integer> assignmentList = new ArrayList<>();
        Map<String, Integer> grades = new HashMap<>();
        when(coursesService.findById(1)).thenReturn(Optional.of(new Courses(1, "Test Course", "Spring", 1,1"2024", "faculty1", studentList, assignmentList,grades)));

        mockMvc.perform(MockMvcRequestBuilders.get("/courses/1"))
                .andExpect(status().isOk());
    }
}
*/