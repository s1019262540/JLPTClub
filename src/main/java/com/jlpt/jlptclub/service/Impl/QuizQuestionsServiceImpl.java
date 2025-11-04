package com.jlpt.jlptclub.service.Impl;

import com.jlpt.jlptclub.domain.QuizQuestions;
import com.jlpt.jlptclub.repository.QuizQuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizQuestionsServiceImpl {
    @Autowired
    private QuizQuestionsRepository quizQuestionsRepository;

    public QuizQuestionsServiceImpl(QuizQuestionsRepository quizQuestionsRepository) {
        this.quizQuestionsRepository = quizQuestionsRepository;
    }

    public Page<QuizQuestions> getQuizQuestions(Pageable pageable) {
//        // 创建分页请求
//        Pageable pageable = PageRequest.of(
//                page, // 页码（从0开始）
//                size, // 每页大小
//                //sortDirection表示分页排序方式,sortField表示分页排序所根据的字段
//                Sort.by(Sort.Direction.fromString(sortDirection), sortField)
//        );

        return quizQuestionsRepository.findAll(pageable);
    }
}
