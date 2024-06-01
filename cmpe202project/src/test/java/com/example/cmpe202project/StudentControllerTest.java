package com.example.cmpe202project;

import com.example.cmpe202project.controller.StudentController;
import com.example.cmpe202project.model.Student;
import com.example.cmpe202project.model.Courses;
import com.example.cmpe202project.model.Assignment;
import com.example.cmpe202project.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void testCreateStudent() throws Exception {
        List<Courses> courseList = new ArrayList<>();
        List<Boolean> profileInfo = new ArrayList<>();
        Map<String, Integer> assignments = new HashMap<>();
        Map<String,Boolean> notifications = new HashMap<>();
        Student student = new Student(1, "Test", "Student", "teststudent@example.com", "password", courseList, profileInfo, assignments, notifications);
        when(studentService.save(student)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"firstName\":\"Test\",\"lastName\":\"Student\",\"email\":\"teststudent@example.com\",\"password\":\"password\",\"courseList\":[],\"profileInfo\":[],\"assignments\":{}}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindStudentById() throws Exception {
        List<Courses> courseList = new ArrayList<>();
        List<Boolean> profileInfo = new ArrayList<>();
        Map<String, Integer> assignments = new HashMap<>();
        Map<String,Boolean> notifications = new HashMap<>();

        when(studentService.findById(1)).thenReturn(Optional.of(new Student(1, "Test", "Student", "teststudent@example.com", "password", courseList, profileInfo, assignments, notifications)));

        mockMvc.perform(MockMvcRequestBuilders.get("/students/1"))
                .andExpect(status().isOk());
    }
}