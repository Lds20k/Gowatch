package com.lucas.gowatch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Channel {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String subscribers_counter;
    private String about;
    private String location;
    private LocalDate creationDate;
}
