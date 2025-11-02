package com.jlpt.jlptclub.domain;

import com.jlpt.jlptclub.utils.Difficulty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "listening_materials")
public class ListeningMaterials {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialId;
    private String title;
    private String varchar;
    private String transcript;
    private Difficulty difficulty;
    private String category;
    private Integer duration;
    private LocalDateTime createdAt;

}
