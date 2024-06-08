package org.example.cinemabackend.cinema.testdata;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.create.CreateProjectionTechnologyRequest;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyResponse;
import org.example.cinemabackend.cinema.core.port.primary.ProjectionTechnologyMapper;
import org.example.cinemabackend.cinema.core.port.secondary.ProjectionTechnologyRepository;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ProjectionTechnologyTestDataProvider {

    private static int projectionTechnologyCounter = 0;
    private final ProjectionTechnologyRepository projectionTechnologyRepository;
    private final ProjectionTechnologyMapper projectionTechnologyMapper;

    public static CreateProjectionTechnologyRequest generateCreateProjectionTechnologyRequest() {
        return new CreateProjectionTechnologyRequest(getTechnology(), "Description");
    }

    public static Set<ProjectionTechnology> generateProjectionTechnologies() {
        return Set.of(
                new ProjectionTechnology(getTechnology(), "Description 1"),
                new ProjectionTechnology(getTechnology(), "Description 2"),
                new ProjectionTechnology(getTechnology(), "Description 3")
        );
    }

    public static List<ProjectionTechnology> generateProjectionTechnologiesList() {
        return List.of(
                new ProjectionTechnology(getTechnology(), "Description 1"),
                new ProjectionTechnology(getTechnology(), "Description 2"),
                new ProjectionTechnology(getTechnology(), "Description 3")
        );
    }

    private static String getTechnology() {
        return "Projection technology No. " + projectionTechnologyCounter++;
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
