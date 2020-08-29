package com.lucas.gowatch.controller.model;

import com.lucas.gowatch.entity.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoResponse {
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
