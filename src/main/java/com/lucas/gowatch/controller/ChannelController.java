package com.lucas.gowatch.controller;

import com.lucas.gowatch.controller.model.ChannelRequest;
import com.lucas.gowatch.controller.model.ChannelResponse;
import com.lucas.gowatch.controller.mapper.Translator;
import com.lucas.gowatch.entity.Channel;
import com.lucas.gowatch.usecase.ConsultAllChannelsUseCase;
import com.lucas.gowatch.usecase.ConsultOneChannelUseCase;
import com.lucas.gowatch.usecase.CreateChannelUsesCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping(path="/channel")
public class ChannelController {

    @Autowired
    private CreateChannelUsesCase createChannelUsesCase;
    @Autowired
    private ConsultAllChannelsUseCase consultAllChannelsUseCase;
    @Autowired
    private ConsultOneChannelUseCase consultOneChannelUseCase;

    @PostMapping
    public ResponseEntity<ChannelResponse> createChannel(@RequestBody ChannelRequest channelRequest){
        Channel channel = Translator.translate(channelRequest, Channel.class);
        ChannelResponse channelResponse = Translator.translate(createChannelUsesCase.execute(channel), ChannelResponse.class);
        return new ResponseEntity<>(channelResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity< List<ChannelResponse> > consultAllChannels(){
        List<ChannelResponse> channelResponseList = Translator.translate(consultAllChannelsUseCase.execute(), ChannelResponse.class);
        return new ResponseEntity<>(channelResponseList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ChannelResponse> consultOneChannel(@PathVariable Long id){
        ChannelResponse channelResponse = Translator.translate(consultOneChannelUseCase.execute(id), ChannelResponse.class);
        return new ResponseEntity<>(channelResponse, HttpStatus.FOUND);
    }
}
