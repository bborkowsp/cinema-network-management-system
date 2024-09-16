package org.example.cinemabackend.movie.core.domain;

public class MovieVariant {
    private Long id;
    private ProjectionTechnologyEnum projectionTechnologyEnum;
    private Language language;

    public MovieVariant(Long id, ProjectionTechnologyEnum projectionTechnologyEnum, Language language) {
        this.id = id;
        this.projectionTechnologyEnum = projectionTechnologyEnum;
        this.language = language;
    }

    public MovieVariant(ProjectionTechnologyEnum projectionTechnologyEnum, Language language) {
        this.projectionTechnologyEnum = projectionTechnologyEnum;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProjectionTechnologyEnum getProjectionTechnologyEnum() {
        return projectionTechnologyEnum;
    }

    public void setProjectionTechnologyEnum(ProjectionTechnologyEnum projectionTechnologyEnum) {
        this.projectionTechnologyEnum = projectionTechnologyEnum;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
