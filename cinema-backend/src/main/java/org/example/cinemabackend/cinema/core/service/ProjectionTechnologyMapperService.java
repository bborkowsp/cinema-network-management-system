package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.CreateProjectionTechnologyRequest;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyResponse;
import org.example.cinemabackend.cinema.core.port.primary.ProjectionTechnologyMapper;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class ProjectionTechnologyMapperService implements ProjectionTechnologyMapper {

    @Override
    public ProjectionTechnologyResponse mapProjectionTechnologyToProjectionTechnologyResponse(ProjectionTechnology projectionTechnology) {
        return ProjectionTechnologyResponse.builder()
                .technology(projectionTechnology.getTechnology())
                .description(projectionTechnology.getDescription())
                .build();
    }

    @Override
    public ProjectionTechnology mapCreateProjectionTechnologyRequestToProjectionTechnology(CreateProjectionTechnologyRequest createProjectionTechnologyRequest) {
        return new ProjectionTechnology(createProjectionTechnologyRequest.technology(), createProjectionTechnologyRequest.description());
    }

    @Override
    public Set<ProjectionTechnologyResponse> mapProjectionTechnologiesToProjectionTechnologyResponses(Set<ProjectionTechnology> projectionTechnologies) {
        return projectionTechnologies.stream().map(this::mapProjectionTechnologyToProjectionTechnologyResponse).collect(java.util.stream.Collectors.toSet());
    }
}
