package org.example.cinemabackend.cinema.application.adapter.primary;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.CreateProjectionTechnologyRequest;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyResponse;
import org.example.cinemabackend.cinema.core.port.primary.ProjectionTechnologyUseCases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/projection-technologies")
@RequiredArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class ProjectionTechnologyController {
    private final ProjectionTechnologyUseCases projectionTechnologyUseCases;

    @GetMapping
    ResponseEntity<Page<ProjectionTechnologyResponse>> getProjectionTechnologies(Pageable pageable) {
        final var projectionTechnologies = projectionTechnologyUseCases.getProjectionTechnologies(pageable);
        return ResponseEntity.ok(projectionTechnologies);
    }

    @GetMapping("/{technology}")
    ResponseEntity<ProjectionTechnologyResponse> getCinema(@PathVariable String technology) {
        final var cinema = projectionTechnologyUseCases.getProjectionTechnology(technology);
        return ResponseEntity.ok(cinema);
    }

    @PostMapping
    ResponseEntity<Void> createCinema(@RequestBody @Valid CreateProjectionTechnologyRequest createCinemaRequest) {
        projectionTechnologyUseCases.createProjectionTechnology(createCinemaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{technology}")
    ResponseEntity<Void> deleteCinema(@PathVariable String technology) {
        projectionTechnologyUseCases.deleteProjectionTechnology(technology);
        return ResponseEntity.noContent().build();
    }
}
