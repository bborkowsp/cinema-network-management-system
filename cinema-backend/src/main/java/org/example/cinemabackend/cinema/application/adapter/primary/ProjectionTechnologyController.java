package org.example.cinemabackend.cinema.application.adapter.primary;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.create.CreateProjectionTechnologyRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateProjectionTechnologyRequest;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyNameResponse;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyResponse;
import org.example.cinemabackend.cinema.core.port.primary.ProjectionTechnologyUseCases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/projection-technologies")
@RequiredArgsConstructor
class ProjectionTechnologyController {

    private final ProjectionTechnologyUseCases projectionTechnologyUseCases;

    @GetMapping
    ResponseEntity<Page<ProjectionTechnologyResponse>> getProjectionTechnologies(Pageable pageable) {
        final var projectionTechnologies = projectionTechnologyUseCases.getProjectionTechnologies(pageable);
        return ResponseEntity.ok(projectionTechnologies);
    }

    @GetMapping("/all")
    ResponseEntity<List<ProjectionTechnologyResponse>> getProjectionTechnologies() {
        final var projectionTechnologies = projectionTechnologyUseCases.getProjectionTechnologies();
        return ResponseEntity.ok(projectionTechnologies);
    }

    @GetMapping("/names")
    ResponseEntity<List<ProjectionTechnologyNameResponse>> getProjectionTechnologiesNames() {
        final var projectionTechnologies = projectionTechnologyUseCases.getProjectionTechnologiesNames();
        return ResponseEntity.ok(projectionTechnologies);
    }

    @GetMapping("/{technology}")
    ResponseEntity<ProjectionTechnologyResponse> getProjectionTechnology(@PathVariable String technology) {
        final var cinema = projectionTechnologyUseCases.getProjectionTechnology(technology);
        return ResponseEntity.ok(cinema);
    }

    @PostMapping
    ResponseEntity<Void> createProjectionTechnology(@RequestBody @Valid CreateProjectionTechnologyRequest createCinemaRequest) {
        projectionTechnologyUseCases.createProjectionTechnology(createCinemaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{technology}")
    ResponseEntity<Void> updateProjectionTechnology(@PathVariable String technology, @RequestBody @Valid UpdateProjectionTechnologyRequest updateProjectionTechnology) {
        projectionTechnologyUseCases.updateProjectionTechnology(technology, updateProjectionTechnology);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{technology}")
    ResponseEntity<Void> deleteProjectionTechnology(@PathVariable String technology) {
        projectionTechnologyUseCases.deleteProjectionTechnology(technology);
        return ResponseEntity.noContent().build();
    }
}