package com.jlpt.jlptclub.service;

import com.jlpt.jlptclub.domain.Vocabulary;
import com.jlpt.jlptclub.repository.VocabularyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocabularyService {
    private final VocabularyRepository vocabularyRepository;

    public VocabularyService(VocabularyRepository vocabularyRepository) {
        this.vocabularyRepository = vocabularyRepository;
    }

    public Vocabulary addVocabulary(Vocabulary vocabulary){
        Vocabulary save = vocabularyRepository.save(vocabulary);
        return save;

    }
    public List<Vocabulary> getVocabulary(){
        List<Vocabulary> all = vocabularyRepository.findAll();
        System.out.println("all"+all);
        return all;
    }
}
