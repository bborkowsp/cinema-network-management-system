package org.example.cinemabackend.cinema.testdata;

import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;

import java.util.Set;

public class ProjectionTechnologyTestDataProvider {

    private static int projectionTechnologyCounter = 0;

    public static Set<ProjectionTechnology> generateProjectionTechnologies() {
        return Set.of(
                new ProjectionTechnology(getTechnology(), "Description 1"),
                new ProjectionTechnology(getTechnology(), "Description 2"),
                new ProjectionTechnology(getTechnology(), "Description 3")
        );
    }

    private static String getTechnology() {
        return "Projection technology No. " + projectionTechnologyCounter++;
    }
}
