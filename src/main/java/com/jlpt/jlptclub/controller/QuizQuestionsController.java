package com.jlpt.jlptclub.controller;

import com.jlpt.jlptclub.domain.QuizQuestions;
import com.jlpt.jlptclub.service.Impl.QuizQuestionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")
public class QuizQuestionsController {
    @Autowired
    private QuizQuestionsServiceImpl quizQuestionsService;

    @GetMapping("/all")
    public Page<QuizQuestions> allQuiz(Pageable pageable){
            // Spring 会自动从请求参数解析分页信息
            // 例如：/users?page=0&size=20&sort=name,desc
            return quizQuestionsService.getQuizQuestions(pageable);
    }
}
