package org.example.cinemabackend.movie.core.domain;

public class Description {
    private String shortDescription;
    private String longDescription;

    public Description(String shortDescription, String longDescription) {
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }
}
