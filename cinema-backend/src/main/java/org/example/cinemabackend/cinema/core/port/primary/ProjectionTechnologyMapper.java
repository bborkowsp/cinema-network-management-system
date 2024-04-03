package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.CreateProjectionTechnologyRequest;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyResponse;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;

public interface ProjectionTechnologyMapper {
    ProjectionTechnologyResponse mapProjectionTechnologyToProjectionTechnologyResponse(ProjectionTechnology projectionTechnology);

    ProjectionTechnology mapCreateProjectionTechnologyRequestToProjectionTechnology(CreateProjectionTechnologyRequest createProjectionTechnologyRequest);
}
