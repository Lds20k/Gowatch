package com.lucas.gowatch.controller.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelRequest {
    private String username;
    private String email;
    private String password;
    private String about;
    private String location;
}
