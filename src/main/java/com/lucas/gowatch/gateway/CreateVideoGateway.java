package com.lucas.gowatch.gateway;

import com.lucas.gowatch.entity.Video;

public interface CreateVideoGateway {
    Video execute(Video video);
}
