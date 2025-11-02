package com.jlpt.jlptclub.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_progress")
public class UserProgress {
    @Id@GeneratedValue
    private Long progressId;
    private Long userId;
    private LocalDateTime date;
    private int vocabStudied;
    private int grammarStudied;
    private int quizzesCompleted;
    private int readingCompleted;
    private int listeningCompleted;
    private int totalStudyTime;
}
