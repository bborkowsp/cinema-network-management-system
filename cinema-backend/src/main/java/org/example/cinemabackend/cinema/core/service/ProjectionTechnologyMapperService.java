package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.CreateProjectionTechnologyRequest;
import org.example.cinemabackend.cinema.application.dto.request.UpdateProjectionTechnologyRequest;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyResponse;
import org.example.cinemabackend.cinema.core.port.primary.ProjectionTechnologyMapper;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
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
        return projectionTechnologies.stream().map(this::mapProjectionTechnologyToProjectionTechnologyResponse).collect(Collectors.toSet());
    }

    @Override
    public Set<ProjectionTechnology> mapCreateProjectionTechnologyRequestsToProjectionTechnologies(Set<ProjectionTechnologyResponse> createProjectionTechnologyRequests) {
        return createProjectionTechnologyRequests.stream().map(this::mapProjectionTechnologyResponseToProjectionTechnology).collect(Collectors.toSet());
    }

    @Override
    public ProjectionTechnology mapProjectionTechnologyResponseToProjectionTechnology(ProjectionTechnologyResponse projectionTechnologyResponse) {
        return new ProjectionTechnology(projectionTechnologyResponse.technology(), projectionTechnologyResponse.description());
    }

    @Override
    public void updateProjectionTechnologyFromUpdateProjectionTechnologyRequest(UpdateProjectionTechnologyRequest updateProjectionTechnologyRequest, ProjectionTechnology projectionTechnology) {
        projectionTechnology.setDescription(updateProjectionTechnologyRequest.description());
        projectionTechnology.setTechnology(updateProjectionTechnologyRequest.technology());
    }
}
