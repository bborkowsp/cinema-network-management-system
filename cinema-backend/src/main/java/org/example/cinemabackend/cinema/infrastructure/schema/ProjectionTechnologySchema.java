package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.example.cinemabackend.cinema.infrastructure.config.AbstractEntitySchema;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;

import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectionTechnologySchema extends AbstractEntitySchema<Long> {

    @Column(nullable = false, length = 50, unique = true)
    private String technology;

    @Column(nullable = false, length = 200)
    private String description;

    public static ProjectionTechnologySchema fromProjectionTechnology(ProjectionTechnology projectionTechnology) {
        return ProjectionTechnologySchema.builder()
                .technology(projectionTechnology.getTechnology())
                .description(projectionTechnology.getDescription())
                .build();
    }

    public ProjectionTechnology toProjectionTechnology() {
        return new ProjectionTechnology(
                this.technology,
                this.description
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectionTechnologySchema that = (ProjectionTechnologySchema) o;
        return Objects.equals(technology, that.technology);
    }

    @Override
    public int hashCode() {
        return Objects.hash(technology);
    }
}
