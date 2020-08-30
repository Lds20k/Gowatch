package com.lucas.gowatch.controller.model;

import com.lucas.gowatch.entity.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoRequest {
    private Long id;
    private Channel channel;
    private String title;
    private String description;
}
