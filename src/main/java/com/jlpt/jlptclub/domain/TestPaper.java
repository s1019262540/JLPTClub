package com.jlpt.jlptclub.domain;

import com.jlpt.jlptclub.utils.CurrentLevel;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;


@Data
public class TestPaper {
    private List<QuizQuestions> readingQuestions;
    private List<QuizQuestions> listeningQuestions;
    private List<QuizQuestions> wordQuestions;
    private List<QuizQuestions> grammarQuestions;
}
