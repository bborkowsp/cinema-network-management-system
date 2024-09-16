package org.example.cinemabackend.movie.core.domain;

public class MovieVariant {
    private Long id;
    private ProjectionTechnology projectionTechnology;
    private Language language;

    public MovieVariant(Long id, ProjectionTechnology projectionTechnology, Language language) {
        this.id = id;
        this.projectionTechnology = projectionTechnology;
        this.language = language;
    }

    public MovieVariant(ProjectionTechnology projectionTechnology, Language language) {
        this.projectionTechnology = projectionTechnology;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProjectionTechnology getProjectionTechnology() {
        return projectionTechnology;
    }

    public void setProjectionTechnology(ProjectionTechnology projectionTechnology) {
        this.projectionTechnology = projectionTechnology;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
