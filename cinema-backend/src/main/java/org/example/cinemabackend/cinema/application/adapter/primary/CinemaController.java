package org.example.cinemabackend.cinema.application.adapter.primary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.resource.CinemaResource;
import org.example.cinemabackend.cinema.core.port.primary.CinemaUseCases;
import org.example.cinemabackend.shared.dto.ResourceList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cinemas")
@RequiredArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class CinemaController {

    private final CinemaUseCases cinemaUseCases;

    @GetMapping
    ResponseEntity<ResourceList<CinemaResource>> getCinemas() {
        final var cinemas = cinemaUseCases.getCinemas();
        return ResponseEntity.ok(new ResourceList<>(cinemas));
    }

    @GetMapping("/{name}")
    ResponseEntity<CinemaResource> getCinema(@PathVariable String name) {
        final var cinema = cinemaUseCases.getCinema(name);
        return ResponseEntity.ok(cinema);
    }
}
