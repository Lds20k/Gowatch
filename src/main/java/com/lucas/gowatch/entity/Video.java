package com.lucas.gowatch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    private Long id;
    private Channel channel;
    private String title;
    private Integer views;
    private String description;
    private LocalDate uploadDate;
    private String videoFile;
    private Integer likes;
    private Integer dislikes;
}
