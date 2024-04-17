package org.example.cinemabackend.movie.core.service;

import org.example.cinemabackend.movie.application.dto.response.VideoFileResponse;
import org.example.cinemabackend.movie.core.domain.VideoFile;
import org.example.cinemabackend.movie.core.port.primary.VideoFileMapper;
import org.springframework.stereotype.Service;

@Service
class VideoFileMapperService implements VideoFileMapper {
    @Override
    public VideoFileResponse mapVideoFileToVideoFileResponse(VideoFile trailer) {
        return VideoFileResponse.builder()
                .data(trailer.getVideo())
                .type(trailer.getType())
                .build();
    }
}
