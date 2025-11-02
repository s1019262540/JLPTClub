package com.jlpt.jlptclub.domain;

import com.jlpt.jlptclub.utils.Difficulty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reading_materials")
public class ReadingMaterials {
    @Id@GeneratedValue
    private Long materialId;
    private String title;
    private String content;
    private Difficulty difficulty;
    private String category;
    private int wordCount;
    private LocalDateTime createdAt;
}
