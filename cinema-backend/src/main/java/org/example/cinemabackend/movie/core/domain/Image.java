package org.example.cinemabackend.movie.core.domain;

public class Image {
    private String name;
    private String type;
    private byte[] data;

    public Image(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public byte[] getData() {
        return this.data;
    }
}
