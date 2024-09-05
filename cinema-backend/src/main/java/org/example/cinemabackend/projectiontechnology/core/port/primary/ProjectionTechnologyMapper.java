package org.example.cinemabackend.projectiontechnology.core.port.primary;

import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.example.cinemabackend.projectiontechnology.application.dto.request.create.CreateProjectionTechnologyRequest;
import org.example.cinemabackend.projectiontechnology.application.dto.request.update.UpdateProjectionTechnologyRequest;
import org.example.cinemabackend.projectiontechnology.application.dto.response.ProjectionTechnologyNameResponse;
import org.example.cinemabackend.projectiontechnology.application.dto.response.ProjectionTechnologyResponse;
import org.springframework.lang.NonNull;

import java.util.Set;

public interface ProjectionTechnologyMapper {
    ProjectionTechnologyResponse mapProjectionTechnologyToProjectionTechnologyResponse(@NonNull ProjectionTechnology projectionTechnology);

    ProjectionTechnologyNameResponse mapProjectionTechnologyToProjectionTechnologyNameResponse(@NonNull ProjectionTechnology projectionTechnology);

    ProjectionTechnology mapCreateProjectionTechnologyRequestToProjectionTechnology(@NonNull CreateProjectionTechnologyRequest createProjectionTechnologyRequest);

    Set<ProjectionTechnologyResponse> mapProjectionTechnologiesToProjectionTechnologyResponses(@NonNull Set<ProjectionTechnology> projectionTechnologies);

    void updateProjectionTechnologyFromUpdateProjectionTechnologyRequest(@NonNull UpdateProjectionTechnologyRequest updateProjectionTechnologyRequest, @NonNull ProjectionTechnology projectionTechnology);
}
