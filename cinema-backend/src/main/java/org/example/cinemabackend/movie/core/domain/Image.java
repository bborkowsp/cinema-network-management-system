package org.example.cinemabackend.movie.core.domain;

public class Image {
    private String name;
    private String type;
    private byte[] image;

    public Image(String name, String type, byte[] image) {
        this.name = name;
        this.type = type;
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public byte[] getImage() {
        return this.image;
    }
}
