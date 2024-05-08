package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.CreateProjectionTechnologyRequest;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectionTechnologyUseCases {
    ProjectionTechnologyResponse getProjectionTechnology(String name);

    Page<ProjectionTechnologyResponse> getProjectionTechnologies(Pageable pageable);

    List<ProjectionTechnologyResponse> getProjectionTechnologies();

    void createProjectionTechnology(CreateProjectionTechnologyRequest createProjectionTechnologyRequest);

    void deleteProjectionTechnology(String technology);
}
