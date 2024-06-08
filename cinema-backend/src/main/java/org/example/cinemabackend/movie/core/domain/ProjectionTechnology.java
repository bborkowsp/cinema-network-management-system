package org.example.cinemabackend.movie.core.domain;

import java.util.Objects;

public class ProjectionTechnology {
    private Long id;
    private String technology;
    private String description;

    public ProjectionTechnology(String technology, String description) {
        this.technology = technology;
        this.description = description;
    }

    public ProjectionTechnology(Long id, String technology, String description) {
        this.id = id;
        this.technology = technology;
        this.description = description;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(technology);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof ProjectionTechnology projectionTechnology)) {
            return false;
        }

        return Objects.equals(technology, projectionTechnology.technology);
    }
}
