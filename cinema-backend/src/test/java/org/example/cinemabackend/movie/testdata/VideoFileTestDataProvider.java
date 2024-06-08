package org.example.cinemabackend.movie.testdata;

import org.example.cinemabackend.movie.core.domain.VideoFile;

public class VideoFileTestDataProvider {

    public static VideoFile generateVideoFile() {
        return new VideoFile("url");
    }
}
