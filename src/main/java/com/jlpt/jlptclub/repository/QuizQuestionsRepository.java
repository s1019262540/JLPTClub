package com.jlpt.jlptclub.repository;

import com.jlpt.jlptclub.domain.QuizQuestions;
import com.jlpt.jlptclub.utils.CurrentLevel;
import com.jlpt.jlptclub.utils.Difficulty;
import com.jlpt.jlptclub.utils.QuestionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizQuestionsRepository extends JpaRepository<QuizQuestions,Long> {

    Page<QuizQuestions> findAll(Pageable pageable);
    Page<QuizQuestions> findByJlptLevelAndQuestionType(CurrentLevel jlptLevel, QuestionType questionType, Pageable pageable);
    List<QuizQuestions> findByJlptLevelAndQuestionType(CurrentLevel jlptLevel, QuestionType questionType, int number);
}
