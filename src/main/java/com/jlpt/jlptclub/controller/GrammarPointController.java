package com.jlpt.jlptclub.controller;

import com.jlpt.jlptclub.domain.GrammarPoints;
import com.jlpt.jlptclub.repository.GrammarPointRepository;
import com.jlpt.jlptclub.service.Impl.GrammarPointServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grammar-point")
public class GrammarPointController {
    private final GrammarPointServiceImpl grammarPointServiceImpl;
    private final GrammarPointRepository grammarPointRepository;

    public GrammarPointController(GrammarPointServiceImpl grammarPointServiceImpl, GrammarPointRepository grammarPointRepository) {
        this.grammarPointServiceImpl = grammarPointServiceImpl;
        this.grammarPointRepository=grammarPointRepository;
    }

    @GetMapping
    public List<GrammarPoints> getGrammarPoints(){
        List<GrammarPoints> all = grammarPointRepository.findAll();
        return all;
    }
}
