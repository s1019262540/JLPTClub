package com.jlpt.jlptclub.repository;

import com.jlpt.jlptclub.domain.QuizQuestions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class QuizQuestionsRepositoryTest {

    @Autowired
    private QuizQuestionsRepository quizQuestionsRepository;

    @Test
    public void findAll(){

    }
}