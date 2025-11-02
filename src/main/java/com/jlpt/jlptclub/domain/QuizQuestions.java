package com.jlpt.jlptclub.domain;

import com.jlpt.jlptclub.utils.Difficulty;
import com.jlpt.jlptclub.utils.QuestionType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="quiz_questions")
public class QuizQuestions {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private QuestionType questionType;
    private String questionText;
    private Difficulty difficulty;
    private Long grammarId;
    private Long wordId;

}
