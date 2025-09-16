package com.example.springSecurityTask.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users") 
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String password; // Will store BCrypt encoded password
    
    // Simple constructor for easy user creation
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
