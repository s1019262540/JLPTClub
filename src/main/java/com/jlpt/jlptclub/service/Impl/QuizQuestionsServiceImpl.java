package com.jlpt.jlptclub.service.Impl;

import com.jlpt.jlptclub.domain.QuizQuestions;
import com.jlpt.jlptclub.domain.TestPaper;
import com.jlpt.jlptclub.repository.QuizQuestionsRepository;
import com.jlpt.jlptclub.service.QuizQuestionsService;
import com.jlpt.jlptclub.utils.CurrentLevel;
import com.jlpt.jlptclub.utils.QuestionType;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizQuestionsServiceImpl implements QuizQuestionsService {
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
        Page<QuizQuestions> quizQuestionsPage = quizQuestionsRepository.findAll(pageable);
        List<QuizQuestions> quizQuestions = quizQuestionsPage.getContent();
        initializeQuestionItems(quizQuestions);
        System.out.println(quizQuestionsPage.getContent());
        return quizQuestionsPage;
    }


    private void initializeQuestionItems(List<QuizQuestions> questions) {
        // 方法1：使用 Hibernate.initialize（需要@BatchSize配合）
        questions.forEach(q -> Hibernate.initialize(q.getOptions()));

        // 方法2：或使用自定义的批量查询方法
        // List<Long> questionIds = questions.stream().map(QuizQuestion::getId).collect(toList());
        // Map<Long, List<QuestionItem>> itemsMap = questionItemRepository.findByQuestionIdIn(questionIds);
        // questions.forEach(q -> q.setItems(itemsMap.get(q.getId())));
    }

    //抽取题目策略
    public List<QuizQuestions> testPaper(CurrentLevel level, QuestionType questionType,Pageable pageable){
        //1.根据对应的级别、类型筛选题目
        Page<QuizQuestions> quizQuestionsPage = quizQuestionsRepository.findByJlptLevelAndQuestionType(level, questionType, pageable);
        quizQuestionsPage.get().forEach(q -> q.getOptions().size());
        List<QuizQuestions> quizQuestions = quizQuestionsPage.getContent();

        return quizQuestions;
    }

    public TestPaper createTestPaper(CurrentLevel level){
        TestPaper testPaper = new TestPaper();
        List<QuizQuestions> listeningList = quizQuestionsRepository.findByJlptLevelAndQuestionType(level, QuestionType.LISTENING, level.getListeningCount());
        List<QuizQuestions> grammarList = quizQuestionsRepository.findByJlptLevelAndQuestionType(level, QuestionType.READING, level.getGrammarCount());
        List<QuizQuestions> readingList = quizQuestionsRepository.findByJlptLevelAndQuestionType(level, QuestionType.GRAMMAR, level.getReadingCount());
        List<QuizQuestions> wordList = quizQuestionsRepository.findByJlptLevelAndQuestionType(level, QuestionType.GRAMMAR, level.getWordCount()) ;
        testPaper.setListeningQuestions(listeningList);
        testPaper.setGrammarQuestions(grammarList);
        testPaper.setReadingQuestions(readingList);
        testPaper.setWordQuestions(wordList);
        return testPaper;
    }
}
