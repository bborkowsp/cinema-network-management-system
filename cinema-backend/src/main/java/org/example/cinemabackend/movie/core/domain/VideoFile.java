package org.example.cinemabackend.movie.core.domain;

public class VideoFile {
    private String name;
    private String type;
    private byte[] video;

    public VideoFile(String name, String type, byte[] video) {
        this.name = name;
        this.type = type;
        this.video = video;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public byte[] getVideo() {
        return video;
    }
}
