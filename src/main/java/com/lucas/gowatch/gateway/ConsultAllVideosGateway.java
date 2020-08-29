package com.lucas.gowatch.gateway;

import com.lucas.gowatch.entity.Video;

import java.util.List;

public interface ConsultAllVideosGateway {
    Iterable<Video> execute();
}
