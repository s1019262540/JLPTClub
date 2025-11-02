package com.jlpt.jlptclub.domain;

import com.jlpt.jlptclub.utils.CurrentLevel;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String email;
    private String password;
    private String avatar;
    @Enumerated(EnumType.STRING)
    private CurrentLevel currentLevel;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
}
