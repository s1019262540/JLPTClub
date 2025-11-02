package com.jlpt.jlptclub.repository;

import com.jlpt.jlptclub.domain.GrammarPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrammarPointRepository extends JpaRepository<GrammarPoints,Long> {
}
