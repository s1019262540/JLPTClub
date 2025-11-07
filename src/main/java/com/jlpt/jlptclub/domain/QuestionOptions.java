package com.jlpt.jlptclub.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "question_options")
@ToString(exclude = "quizQuestions")
public class QuestionOptions {
    @Id@GeneratedValue
    private Long optionId;
    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonBackReference
    private QuizQuestions quizQuestions;
    private String optionText;
    private Boolean isCorrect;
    private String explanation;

}
