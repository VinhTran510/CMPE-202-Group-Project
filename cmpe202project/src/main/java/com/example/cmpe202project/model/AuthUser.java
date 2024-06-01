package com.example.cmpe202project.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("authUser")
public class AuthUser {
    @Id
    private String id;
    @Indexed
    private String username;
    private String password;
    private String role;  // Add role field
}
