package com.lucas.gowatch.usecase;

import com.lucas.gowatch.gateway.DeleteChannelGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteChannelUseCase {

    @Autowired
    DeleteChannelGateway gateway;

    public String execute(Long id) {
        return gateway.execute(id);
    }
}
