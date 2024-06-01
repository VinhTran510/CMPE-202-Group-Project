package com.example.cmpe202project.controller;

import lombok.AllArgsConstructor;
import com.example.cmpe202project.model.AuthUser;
import com.example.cmpe202project.repository.AuthUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class UserController {

    private final AuthUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody AuthUser user) {
        try {
            if (userRepository.findByUsername(user.getUsername()).isPresent())
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken. Please try again");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            AuthUser save = userRepository.save(user);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/faculty/home")
    public String facultyHome() {
        return "faculty_home";  // This should match the name of your view (e.g., a .html file in src/main/resources/templates if you're using Thymeleaf)
    }


    @GetMapping("/student/home")
    public String studentHome() {
        return "student_home";  // Ensure this view exists
    }

    @GetMapping("/admin/home")
    public String adminHome() {
        return "admin_home";  // Ensure this view exists
    }


}