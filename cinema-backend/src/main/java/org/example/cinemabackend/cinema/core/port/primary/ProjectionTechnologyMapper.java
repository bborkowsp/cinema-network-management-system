package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.CreateProjectionTechnologyRequest;
import org.example.cinemabackend.cinema.application.dto.request.UpdateProjectionTechnologyRequest;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyNameResponse;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyResponse;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.springframework.lang.NonNull;

import java.util.Set;

public interface ProjectionTechnologyMapper {
    ProjectionTechnologyResponse mapProjectionTechnologyToProjectionTechnologyResponse(ProjectionTechnology projectionTechnology);

    ProjectionTechnologyNameResponse mapProjectionTechnologyToProjectionTechnologyNameResponse(ProjectionTechnology projectionTechnology);


    ProjectionTechnology mapCreateProjectionTechnologyRequestToProjectionTechnology(@NonNull CreateProjectionTechnologyRequest createProjectionTechnologyRequest);

    void updateProjectionTechnologyFromUpdateProjectionTechnologyRequest(@NonNull UpdateProjectionTechnologyRequest updateProjectionTechnologyRequest, @NonNull ProjectionTechnology projectionTechnology);

    Set<ProjectionTechnologyResponse> mapProjectionTechnologiesToProjectionTechnologyResponses(Set<ProjectionTechnology> projectionTechnologies);

}
