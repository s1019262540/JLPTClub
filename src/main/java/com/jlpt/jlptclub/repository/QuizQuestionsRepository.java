package com.jlpt.jlptclub.repository;

import com.jlpt.jlptclub.domain.QuizQuestions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionsRepository extends JpaRepository<QuizQuestions,Long> {

    Page<QuizQuestions> findAll(Pageable pageable);
}
