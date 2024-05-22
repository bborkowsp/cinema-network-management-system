package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.CreateProjectionTechnologyRequest;
import org.example.cinemabackend.cinema.application.dto.request.UpdateProjectionTechnologyRequest;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyNameResponse;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyResponse;
import org.example.cinemabackend.cinema.core.port.primary.ProjectionTechnologyMapper;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class ProjectionTechnologyMapperService implements ProjectionTechnologyMapper {


    @Override
    public ProjectionTechnologyResponse mapProjectionTechnologyToProjectionTechnologyResponse(
            ProjectionTechnology projectionTechnology
    ) {
        return ProjectionTechnologyResponse.builder()
                .technology(projectionTechnology.getTechnology())
                .description(projectionTechnology.getDescription())
                .build();
    }

    @Override
    public ProjectionTechnologyNameResponse mapProjectionTechnologyToProjectionTechnologyNameResponse
            (ProjectionTechnology projectionTechnology
            ) {
        return ProjectionTechnologyNameResponse.builder()
                .technology(projectionTechnology.getTechnology())
                .build();
    }

    @Override
    public ProjectionTechnology mapCreateProjectionTechnologyRequestToProjectionTechnology(
            @NonNull CreateProjectionTechnologyRequest createProjectionTechnologyRequest
    ) {
        return new ProjectionTechnology(
                createProjectionTechnologyRequest.technology(),
                createProjectionTechnologyRequest.description()
        );
    }

    @Override
    public void updateProjectionTechnologyFromUpdateProjectionTechnologyRequest(
            @NonNull UpdateProjectionTechnologyRequest updateProjectionTechnologyRequest,
            @NonNull ProjectionTechnology projectionTechnology
    ) {
        projectionTechnology.setDescription(updateProjectionTechnologyRequest.description());
        projectionTechnology.setTechnology(updateProjectionTechnologyRequest.technology());
    }

    @Override
    public Set<ProjectionTechnologyResponse> mapProjectionTechnologiesToProjectionTechnologyResponses(
            Set<ProjectionTechnology> projectionTechnologies
    ) {
        return projectionTechnologies.stream()
                .map(this::mapProjectionTechnologyToProjectionTechnologyResponse)
                .collect(Collectors.toSet());
    }
}