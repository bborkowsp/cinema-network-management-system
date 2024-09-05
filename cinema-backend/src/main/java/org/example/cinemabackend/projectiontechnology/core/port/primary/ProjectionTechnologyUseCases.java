package org.example.cinemabackend.projectiontechnology.core.port.primary;

import org.example.cinemabackend.projectiontechnology.application.dto.request.create.CreateProjectionTechnologyRequest;
import org.example.cinemabackend.projectiontechnology.application.dto.request.update.UpdateProjectionTechnologyRequest;
import org.example.cinemabackend.projectiontechnology.application.dto.response.ProjectionTechnologyNameResponse;
import org.example.cinemabackend.projectiontechnology.application.dto.response.ProjectionTechnologyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectionTechnologyUseCases {

    Page<ProjectionTechnologyResponse> getProjectionTechnologies(Pageable pageable);

    List<ProjectionTechnologyResponse> getProjectionTechnologies();

    List<ProjectionTechnologyNameResponse> getProjectionTechnologiesNames();

    ProjectionTechnologyResponse getProjectionTechnology(String name);

    void createProjectionTechnology(CreateProjectionTechnologyRequest createProjectionTechnologyRequest);

    void deleteProjectionTechnology(String technology);

    void updateProjectionTechnology(String technology, UpdateProjectionTechnologyRequest createCinemaRequest);

}
