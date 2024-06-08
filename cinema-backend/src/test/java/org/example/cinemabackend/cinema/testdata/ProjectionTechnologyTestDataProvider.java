package org.example.cinemabackend.cinema.testdata;

import org.example.cinemabackend.cinema.application.dto.request.create.CreateProjectionTechnologyRequest;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;

import java.util.List;

public class ProjectionTechnologyTestDataProvider {

    private static int projectionTechnologyCounter = 0;

    public static CreateProjectionTechnologyRequest generateCreateProjectionTechnologyRequest() {
        return new CreateProjectionTechnologyRequest(getTechnology(), "Description");
    }

    public static List<ProjectionTechnology> generateProjectionTechnologies() {
        return List.of(
                new ProjectionTechnology(getTechnology(), "Description 1"),
                new ProjectionTechnology(getTechnology(), "Description 2"),
                new ProjectionTechnology(getTechnology(), "Description 3")
        );
    }

    private static String getTechnology() {
        return "Projection technology No. " + projectionTechnologyCounter++;
    }
}
