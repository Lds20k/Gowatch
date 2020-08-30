package com.lucas.gowatch.usecase;

import com.lucas.gowatch.gateway.DeleteVideoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteVideoUseCase {

    @Autowired
    DeleteVideoGateway gateway;

    public String execute(Long id) {
        return gateway.execute(id);
    }
}
