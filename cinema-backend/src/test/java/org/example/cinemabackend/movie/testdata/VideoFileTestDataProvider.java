package org.example.cinemabackend.movie.testdata;

import org.example.cinemabackend.movie.application.dto.request.VideoFileRequest;
import org.example.cinemabackend.movie.core.domain.VideoFile;

public class VideoFileTestDataProvider {

    public static VideoFile generateVideoFile() {
        return new VideoFile("url");
    }

    public static VideoFileRequest generateVideoFileRequest() {
        return VideoFileRequest.builder()
                .url("url")
                .build();
    }
}
