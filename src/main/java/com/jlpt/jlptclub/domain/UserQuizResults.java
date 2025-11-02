package com.jlpt.jlptclub.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_quiz_results")
public class UserQuizResults {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;
    private Long userId;
    private Long questionId;
    private Long selectedOptionId;
    private Boolean isCorrect;
    private int timeSpent;
    private LocalDateTime answeredAt;
}
