package com.example.cmpe202project;

import com.example.cmpe202project.controller.AssignmentController;
import com.example.cmpe202project.model.Assignment;
import com.example.cmpe202project.service.AssignmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AssignmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssignmentService assignmentService;

    @Test
    public void testCreateAssignment() throws Exception {
        Assignment assignment = new Assignment(1, "Test Assignment", false, false, null);
        when(assignmentService.save(assignment)).thenReturn(assignment);

        mockMvc.perform(MockMvcRequestBuilders.post("/assignments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"title\":\"Test Assignment\",\"isQuiz\":false,\"isPublished\":false,\"work\":null}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindAllAssignments() throws Exception {
        when(assignmentService.findAll()).thenReturn(Arrays.asList(new Assignment(1, "Test Assignment", false, false, null)));

        mockMvc.perform(MockMvcRequestBuilders.get("/assignments"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindAssignmentById() throws Exception {
        when(assignmentService.findById(1)).thenReturn(Optional.of(new Assignment(1, "Test Assignment", false, false, null)));

        mockMvc.perform(MockMvcRequestBuilders.get("/assignments/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAssignmentById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/assignments/1"))
                .andExpect(status().isOk());
    }
}