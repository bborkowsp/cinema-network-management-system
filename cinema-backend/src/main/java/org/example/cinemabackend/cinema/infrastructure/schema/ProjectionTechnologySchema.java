package org.example.cinemabackend.cinema.infrastructure.schema;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;

import java.util.Objects;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectionTechnologySchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String technology;

    @Column(nullable = false, length = 200)
    private String description;

    public static ProjectionTechnologySchema fromProjectionTechnology(ProjectionTechnology projectionTechnology) {
        return ProjectionTechnologySchema.builder()
                .id(projectionTechnology.getId())
                .technology(projectionTechnology.getTechnology())
                .description(projectionTechnology.getDescription())
                .build();
    }

    public ProjectionTechnology toProjectionTechnology() {
        return new ProjectionTechnology(
                this.id,
                this.technology,
                this.description
        );
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

        if (!(object instanceof ProjectionTechnologySchema projectionTechnologySchema)) {
            return false;
        }

        return Objects.equals(technology, projectionTechnologySchema.technology);
    }
}
