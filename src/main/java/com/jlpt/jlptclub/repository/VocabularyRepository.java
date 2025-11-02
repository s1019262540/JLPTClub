package com.jlpt.jlptclub.repository;

import com.jlpt.jlptclub.domain.Vocabulary;
import com.jlpt.jlptclub.utils.CurrentLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary,Long> {
    Vocabulary findByJapanese(String japanese);
    List<Vocabulary> findByCategory(String category);
    List<Vocabulary> findByJlptLevel(CurrentLevel currentLevel);

}
