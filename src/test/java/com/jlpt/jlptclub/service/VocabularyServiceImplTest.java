package com.jlpt.jlptclub.service;

import com.jlpt.jlptclub.domain.Vocabulary;
import com.jlpt.jlptclub.service.Impl.VocabularyServiceImpl;
import com.jlpt.jlptclub.utils.CurrentLevel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class VocabularyServiceImplTest {
    @Autowired
    private VocabularyServiceImpl vocabularyServiceImpl;


    @Test
    void addVocabulary() {
        Vocabulary vocabulary=new Vocabulary();
        vocabulary.setJlptLevel(CurrentLevel.N2);
        vocabulary.setCategory("日常用语");
        vocabulary.setJapanese("しばらく");
        vocabulary.setMeaning("暂时");
        vocabulary.setAddedDate(LocalDateTime.now());
        Vocabulary vocabulary1 = vocabularyServiceImpl.addVocabulary(vocabulary);
        System.out.println(vocabulary1);
    }
    @Test
    void getVocabulary(){
        List<Vocabulary> vocabulary = vocabularyServiceImpl.getVocabulary();
        System.out.println("======================================="+vocabulary);
}
}