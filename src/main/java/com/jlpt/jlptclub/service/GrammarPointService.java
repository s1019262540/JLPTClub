package com.jlpt.jlptclub.service;

import com.jlpt.jlptclub.repository.GrammarPointRepository;
import org.springframework.stereotype.Service;

@Service
public class GrammarPointService {
    private final GrammarPointRepository grammarPointRepository;

    public GrammarPointService(GrammarPointRepository grammarPointRepository) {
        this.grammarPointRepository = grammarPointRepository;
    }



}
