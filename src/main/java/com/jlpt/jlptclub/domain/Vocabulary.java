package com.jlpt.jlptclub.domain;

import com.jlpt.jlptclub.utils.CurrentLevel;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "vocabulary")
public class Vocabulary {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wordId;
    private String japanese;
    private String reading;
    private String meaning;
    private String partOfSpeech;
    @Enumerated(EnumType.STRING)
    private CurrentLevel jlptLevel;
    private String category;
    private String exampleSentence;
    private String exampleTranslation;
    private String memoryTip;
    private LocalDateTime addedDate;

}
