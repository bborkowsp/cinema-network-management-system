package org.example.cinemabackend.movie.core.domain;

public class ProjectionTechnology {
    private String technology;
    private String description;

    public ProjectionTechnology(String technology, String description) {
        this.technology = technology;
        this.description = description;
    }

    public String getTechnology() {
        return technology;
    }

    public String getDescription() {
        return description;
    }

}
