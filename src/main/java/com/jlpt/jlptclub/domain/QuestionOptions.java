package com.jlpt.jlptclub.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "question_options")
public class QuestionOptions {
    @Id@GeneratedValue
    private Long optionId;
    private Long questionId;
    private String optionText;
    private Boolean isCorrect;
    private String explanation;
}
