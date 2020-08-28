package com.lucas.gowatch.gateway;

import com.lucas.gowatch.entity.Channel;

import java.util.Optional;

public interface ConsultOneChannelGateway {
    Channel execute(Long id);
}
