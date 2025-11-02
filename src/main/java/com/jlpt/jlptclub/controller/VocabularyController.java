package com.jlpt.jlptclub.controller;

import com.jlpt.jlptclub.domain.Vocabulary;
import com.jlpt.jlptclub.repository.VocabularyRepository;
import com.jlpt.jlptclub.utils.CurrentLevel;
import jakarta.validation.Valid;
import lombok.experimental.PackagePrivate;
import org.springframework.core.SpringVersion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vocabulary")
public class VocabularyController {
    private VocabularyRepository vocabularyRepository;

    public VocabularyController(VocabularyRepository vocabularyRepository){
        this.vocabularyRepository=vocabularyRepository;
    }

    @GetMapping
    public List<Vocabulary> getAllVocabulary(){
        List<Vocabulary> all = vocabularyRepository.findAll();
        return all;
    }

    @GetMapping("/{id}")
    public Vocabulary getVocabulary(@PathVariable("id")Long id){
        Vocabulary vocabulary = vocabularyRepository.findById(id).get();
        return vocabulary;
    }

    @GetMapping("search/{keyword}")
    public Vocabulary vocabularySearch(@PathVariable("keyword") String keyword){
        Vocabulary japanese = vocabularyRepository.findByJapanese(keyword);
        return japanese;
    }
    @GetMapping("/by-category/{category}")
    public List<Vocabulary> getVocabularyByCategory(@PathVariable("category")String category){
        List<Vocabulary> vocabularyList = vocabularyRepository.findByCategory(category);
        return vocabularyList;
    }

    //level:N1,N2,N3,N4,N5
    @GetMapping("/by-level/{level}")
    public List<Vocabulary> getVocabularyByLevel(@PathVariable("level")String level) throws Exception {
        CurrentLevel currentLevel = CurrentLevel.valueOf(level);
        List<Vocabulary> vocabularyList = vocabularyRepository.findByJlptLevel(currentLevel);
        return vocabularyList;
    }
    @PutMapping("/{id}")
    public ResponseEntity<Vocabulary> putVocabulary(@PathVariable("id")Long id,@Valid @RequestBody Vocabulary vocabulary){
        Vocabulary save = vocabularyRepository.save(vocabulary);
        return ResponseEntity.ok(save);
    }

}
