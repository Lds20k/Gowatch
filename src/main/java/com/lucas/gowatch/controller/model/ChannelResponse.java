package com.lucas.gowatch.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelResponse {
    private String id;
    private String username;
    private String email;
    private String password;
    private String subscribersCounter;
    private String about;
    private String location;
    private String creationDate;
}
