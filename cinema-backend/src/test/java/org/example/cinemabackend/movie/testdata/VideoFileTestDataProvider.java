package org.example.cinemabackend.movie.testdata;

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
