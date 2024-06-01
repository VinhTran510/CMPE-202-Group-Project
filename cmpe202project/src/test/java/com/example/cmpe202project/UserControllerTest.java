package com.example.cmpe202project;

import com.example.cmpe202project.controller.UserController;
import com.example.cmpe202project.model.User;
import com.example.cmpe202project.service.UserService;
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
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testCreateUser() throws Exception {
        User user = new User(1, "Test User","Test", "testuser@example.com", "password");
        when(userService.save(user)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"firstName\":\"Test User\",\"lastName\":\"Test\",\"email\":\"testuser@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindUserById() throws Exception {
        when(userService.findById(1)).thenReturn(Optional.of(new User(1, "Test User","Test", "testuser@example.com", "password")));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(status().isOk());
    }
}