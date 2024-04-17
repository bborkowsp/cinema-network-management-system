package org.example.cinemabackend.movie.core.port.primary;

import org.example.cinemabackend.movie.application.dto.response.VideoFileResponse;
import org.example.cinemabackend.movie.core.domain.VideoFile;

public interface VideoFileMapper {
    VideoFileResponse mapVideoFileToVideoFileResponse(VideoFile trailer);
}
