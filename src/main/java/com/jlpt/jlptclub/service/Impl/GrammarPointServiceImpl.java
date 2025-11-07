package com.jlpt.jlptclub.service.Impl;

import com.jlpt.jlptclub.repository.GrammarPointRepository;
import com.jlpt.jlptclub.service.GrammarPointService;
import org.springframework.stereotype.Service;

@Service
public class GrammarPointServiceImpl implements GrammarPointService {
    private final GrammarPointRepository grammarPointRepository;

    public GrammarPointServiceImpl(GrammarPointRepository grammarPointRepository) {
        this.grammarPointRepository = grammarPointRepository;
    }



}
