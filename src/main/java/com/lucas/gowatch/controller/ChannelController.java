package com.lucas.gowatch.controller;

import com.lucas.gowatch.controller.model.ChannelRequest;
import com.lucas.gowatch.controller.model.ChannelResponse;
import com.lucas.gowatch.controller.mapper.Translator;
import com.lucas.gowatch.entity.Channel;
import com.lucas.gowatch.usecase.CreateChannelUsesCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/channel")
@Component
public class ChannelController {

    @Autowired
    private CreateChannelUsesCases createChannelUsesCases;

    @PostMapping
    public ResponseEntity<ChannelResponse> createChannel(@RequestBody ChannelRequest channelRequest){
        Channel channel = Translator.translate(channelRequest, Channel.class);
        ChannelResponse channelResponse = Translator.translate(createChannelUsesCases.execute(channel), ChannelResponse.class);
        ResponseEntity<ChannelResponse> response = new ResponseEntity<>(channelResponse, HttpStatus.CREATED);
        return response;
    }
}
