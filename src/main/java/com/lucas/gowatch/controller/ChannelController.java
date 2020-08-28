package com.lucas.gowatch.controller;

import com.lucas.gowatch.controller.model.ChannelRequest;
import com.lucas.gowatch.controller.model.ChannelResponse;
import com.lucas.gowatch.controller.mapper.Translator;
import com.lucas.gowatch.entity.Channel;
import com.lucas.gowatch.gateway.ConsultAllChannelsGateway;
import com.lucas.gowatch.gateway.ConsultOneChannelGateway;
import com.lucas.gowatch.usecase.CreateChannelUsesCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/channel")
@Component
public class ChannelController {

    @Autowired
    private CreateChannelUsesCases createChannelUsesCases;
    @Autowired
    private ConsultAllChannelsGateway consultAllChannelsUseCase;
    @Autowired
    private ConsultOneChannelGateway consultOneChannelGateway;

    @PostMapping
    public ResponseEntity<ChannelResponse> createChannel(@RequestBody ChannelRequest channelRequest){
        Channel channel = Translator.translate(channelRequest, Channel.class);
        ChannelResponse channelResponse = Translator.translate(createChannelUsesCases.execute(channel), ChannelResponse.class);
        return new ResponseEntity<>(channelResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity< List<ChannelResponse> > consultAllChannels(){
        @SuppressWarnings("unchecked")
        List<ChannelResponse> channelResponseList = Translator.translate(consultAllChannelsUseCase.execute(), ChannelResponse.class);
        return new ResponseEntity<>(channelResponseList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ChannelResponse> consultOneChannel(@PathVariable Long id){
        ChannelResponse channelResponse = Translator.translate(consultOneChannelGateway.execute(id), ChannelResponse.class);
        return new ResponseEntity<>(channelResponse, HttpStatus.FOUND);
    }
}
