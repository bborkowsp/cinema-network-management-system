package org.example.cinemabackend.cinema.core.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.CreateProjectionTechnologyRequest;
import org.example.cinemabackend.cinema.application.dto.request.UpdateProjectionTechnologyRequest;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyResponse;
import org.example.cinemabackend.cinema.core.port.primary.ProjectionTechnologyMapper;
import org.example.cinemabackend.cinema.core.port.primary.ProjectionTechnologyUseCases;
import org.example.cinemabackend.cinema.core.port.secondary.ProjectionTechnologyRepository;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class ProjectionTechnologyService implements ProjectionTechnologyUseCases {

    private final ProjectionTechnologyRepository projectionTechnologyRepository;
    private final ProjectionTechnologyMapper projectionTechnologyMapper;
    private final MovieRepository movieRepository;

    @Override
    @Transactional(readOnly = true)
    public ProjectionTechnologyResponse getProjectionTechnology(String technology) {
        final var projectionTechnology = projectionTechnologyRepository.findByTechnology(technology).orElseThrow();
        return projectionTechnologyMapper.mapProjectionTechnologyToProjectionTechnologyResponse(projectionTechnology);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProjectionTechnologyResponse> getProjectionTechnologies(Pageable pageable) {
        return projectionTechnologyRepository.findAll(pageable).map(projectionTechnologyMapper::mapProjectionTechnologyToProjectionTechnologyResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectionTechnologyResponse> getProjectionTechnologies() {
        return projectionTechnologyRepository.findAll().stream()
                .map(projectionTechnologyMapper::mapProjectionTechnologyToProjectionTechnologyResponse)
                .toList();
    }

    @Override
    @Transactional
    public void createProjectionTechnology(CreateProjectionTechnologyRequest createProjectionTechnologyRequest) {
        validateProjectionTechnologyDoesntExist(createProjectionTechnologyRequest.technology());
        final var projectionTechnology = projectionTechnologyMapper.mapCreateProjectionTechnologyRequestToProjectionTechnology(createProjectionTechnologyRequest);
        projectionTechnologyRepository.save(projectionTechnology);
    }

    @Override
    @Transactional
    public void deleteProjectionTechnology(String technology) {
        validateProjectionTechnologyExists(technology);
        validateProjectionTechnologyIsNotUsedInAnyMovie(technology);
        projectionTechnologyRepository.deleteByTechnology(technology);
    }

    @Override
    @Transactional
    public void updateProjectionTechnology(String technology, UpdateProjectionTechnologyRequest createCinemaRequest) {
        final var projectionTechnology = projectionTechnologyRepository.findByTechnology(technology).orElseThrow();
        validateProjectionTechnologyDoesntExist(createCinemaRequest.technology());
        projectionTechnologyMapper.updateProjectionTechnologyFromUpdateProjectionTechnologyRequest(createCinemaRequest, projectionTechnology);
        projectionTechnologyRepository.save(projectionTechnology);
    }

    private void validateProjectionTechnologyIsNotUsedInAnyMovie(String technology) {
        if (movieRepository.findByProjectionTechnology(technology)) {
            throw new IllegalArgumentException("Projection technology is used in a movie");
        }
    }

    private void validateProjectionTechnologyExists(String technology) {
        if (projectionTechnologyRepository.findByTechnology(technology).isEmpty()) {
            throw new NoSuchElementException("Projection technology '" + technology + "' does not exist");
        }
    }

    private void validateProjectionTechnologyDoesntExist(String technology) {
        if (projectionTechnologyRepository.findByTechnology(technology).isPresent()) {
            throw new IllegalArgumentException("Projection technology already exists");
        }
    }
}
