package com.jlpt.jlptclub.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_vocab_progress")
public class UserVocabProgress {
    @Id@GeneratedValue
    private Long progressId;
    private Long userId;
    private Long wordId;
    private Integer familiarityScore;
    private LocalDateTime lastReviewed;
    private LocalDateTime nextReviewDate;
    private Integer reviewCount;
}
