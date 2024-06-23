package org.example.cinemabackend.cinema.core.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.create.CreateProjectionTechnologyRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateProjectionTechnologyRequest;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyNameResponse;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyResponse;
import org.example.cinemabackend.cinema.core.port.primary.ProjectionTechnologyMapper;
import org.example.cinemabackend.cinema.core.port.primary.ProjectionTechnologyUseCases;
import org.example.cinemabackend.cinema.core.port.secondary.ProjectionTechnologyRepository;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class ProjectionTechnologyService implements ProjectionTechnologyUseCases {
    private final ProjectionTechnologyRepository projectionTechnologyRepository;
    private final ProjectionTechnologyMapper projectionTechnologyMapper;
    private final MovieRepository movieRepository;

    @Override
    public Page<ProjectionTechnologyResponse> getProjectionTechnologies(Pageable pageable) {
        return projectionTechnologyRepository.findProjectionTechnologyPage(pageable)
                .map(projectionTechnologyMapper::mapProjectionTechnologyToProjectionTechnologyResponse);
    }

    @Override
    public List<ProjectionTechnologyResponse> getProjectionTechnologies() {
        return projectionTechnologyRepository.findAll().stream()
                .map(projectionTechnologyMapper::mapProjectionTechnologyToProjectionTechnologyResponse)
                .toList();
    }

    @Override
    public List<ProjectionTechnologyNameResponse> getProjectionTechnologiesNames() {
        return projectionTechnologyRepository.findAll().stream()
                .map(projectionTechnologyMapper::mapProjectionTechnologyToProjectionTechnologyNameResponse)
                .toList();
    }

    @Override
    public ProjectionTechnologyResponse getProjectionTechnology(String technology) {
        final var projectionTechnology = projectionTechnologyRepository.findByTechnology(technology).orElseThrow();
        return projectionTechnologyMapper.mapProjectionTechnologyToProjectionTechnologyResponse(projectionTechnology);
    }

    @Override
    public void createProjectionTechnology(CreateProjectionTechnologyRequest createProjectionTechnologyRequest) {
        validateProjectionTechnologyDoesntExist(createProjectionTechnologyRequest.technology());

        final var projectionTechnology = projectionTechnologyMapper
                .mapCreateProjectionTechnologyRequestToProjectionTechnology(createProjectionTechnologyRequest);
        projectionTechnologyRepository.save(projectionTechnology);
    }

    @Override
    public void deleteProjectionTechnology(String technology) {
        validateProjectionTechnologyExists(technology);
        validateProjectionTechnologyIsNotUsedInAnyMovie(technology);

        projectionTechnologyRepository.deleteByTechnology(technology);
    }

    @Override
    public void updateProjectionTechnology(
            String technology, UpdateProjectionTechnologyRequest updateProjectionTechnologyRequest
    ) {
        validateProjectionTechnologyIsNotTaken(technology, updateProjectionTechnologyRequest.technology());

        final var projectionTechnology = projectionTechnologyRepository.findByTechnology(technology).orElseThrow();
        projectionTechnologyMapper.updateProjectionTechnologyFromUpdateProjectionTechnologyRequest(
                updateProjectionTechnologyRequest, projectionTechnology);
        projectionTechnologyRepository.save(projectionTechnology);
    }

    private void validateProjectionTechnologyDoesntExist(String technology) {
        if (projectionTechnologyRepository.existsByTechnology(technology)) {
            throw new IllegalStateException("Projection technology '" + technology + "' already exists");
        }
    }

    private void validateProjectionTechnologyIsNotTaken(String oldTechnology, String newTechnology) {
        if (!oldTechnology.equals(newTechnology) && projectionTechnologyRepository.existsByTechnology(newTechnology)) {
            throw new IllegalStateException("Projection technology '" + newTechnology + "' already exists");
        }
    }

    private void validateProjectionTechnologyExists(String technology) {
        if (projectionTechnologyRepository.findByTechnology(technology).isEmpty()) {
            throw new NoSuchElementException("Projection technology '" + technology + "' does not exist");
        }
    }

    private void validateProjectionTechnologyIsNotUsedInAnyMovie(String technology) {
        if (movieRepository.existsByProjectionTechnology(technology)) {
            throw new IllegalStateException("Cannot delete projection technology '" + technology + "' because it is used in some movies");
        }
    }
}