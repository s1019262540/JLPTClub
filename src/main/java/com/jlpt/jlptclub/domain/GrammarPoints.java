package com.jlpt.jlptclub.domain;

import com.jlpt.jlptclub.utils.CurrentLevel;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "grammar_points")
public class GrammarPoints {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long grammarId;
    private String grammarPoint;
    private String structure;
    private String meaning;
    @Enumerated(EnumType.STRING)
    private CurrentLevel jlptLevel;
    private String exampleSentence;
    private String exampleTranslation;
    private String notes;
}
