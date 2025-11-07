package com.jlpt.jlptclub.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jlpt.jlptclub.utils.Difficulty;
import com.jlpt.jlptclub.utils.QuestionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

import java.util.List;

@Data
@Entity
@Table(name="quiz_questions")
@ToString(exclude = "questionOptions")
public class QuizQuestions {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private QuestionType questionType;
    private String questionText;
    private Difficulty difficulty;
    private Long grammarId;
    private Long wordId;
    @OneToMany(mappedBy = "quizQuestions",fetch = FetchType.LAZY)
    @BatchSize(size=20)
    @JsonManagedReference
    private List<QuestionOptions> options;

}
