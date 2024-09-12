package org.example.cinemabackend.cinema.testdata;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.example.cinemabackend.projectiontechnology.application.dto.request.create.CreateProjectionTechnologyRequest;
import org.example.cinemabackend.projectiontechnology.application.dto.response.ProjectionTechnologyResponse;
import org.example.cinemabackend.projectiontechnology.core.port.primary.ProjectionTechnologyMapper;
import org.example.cinemabackend.projectiontechnology.core.port.secondary.ProjectionTechnologyRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ProjectionTechnologyTestDataProvider {

    private static final String PROJECTION_TECHNOLOGY = "Projection technology No. ";
    private static final String DESCRIPTION = "Description";
    private static int projectionTechnologyCounter = 0;
    private final ProjectionTechnologyRepository projectionTechnologyRepository;
    private final ProjectionTechnologyMapper projectionTechnologyMapper;

    public static CreateProjectionTechnologyRequest generateCreateProjectionTechnologyRequest() {
        return new CreateProjectionTechnologyRequest(getTechnology(), DESCRIPTION);
    }

    private static String getTechnology() {
        return PROJECTION_TECHNOLOGY + projectionTechnologyCounter++;
    }

    public static Set<ProjectionTechnology> generateProjectionTechnologies() {
        return Set.of(
                new ProjectionTechnology(getTechnology(), DESCRIPTION),
                new ProjectionTechnology(getTechnology(), DESCRIPTION),
                new ProjectionTechnology(getTechnology(), DESCRIPTION)
        );
    }

    public static List<ProjectionTechnology> generateProjectionTechnologiesList() {
        return List.of(
                new ProjectionTechnology(getTechnology(), DESCRIPTION),
                new ProjectionTechnology(getTechnology(), DESCRIPTION),
                new ProjectionTechnology(getTechnology(), DESCRIPTION)
        );
    }

    public Set<ProjectionTechnologyResponse> generateProjectionTechnologiesResponse() {
        final var firstProjectionTechnology = projectionTechnologyRepository.findAll().getFirst();
        final var lastProjectionTechnology = projectionTechnologyRepository.findAll().getLast();
        return Set.of(
                projectionTechnologyMapper.mapProjectionTechnologyToProjectionTechnologyResponse(firstProjectionTechnology),
                projectionTechnologyMapper.mapProjectionTechnologyToProjectionTechnologyResponse(lastProjectionTechnology)
        );
    }

    public void saveProjectionTechnologiesToDatabase(Set<ProjectionTechnology> projectionTechnologies) {
        projectionTechnologies.forEach(projectionTechnologyRepository::save);
    }
}
