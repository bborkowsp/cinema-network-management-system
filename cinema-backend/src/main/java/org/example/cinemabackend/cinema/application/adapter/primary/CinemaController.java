package org.example.cinemabackend.cinema.application.adapter.primary;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.CreateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.response.CinemaResponse;
import org.example.cinemabackend.cinema.application.dto.response.CinemaTableResponse;
import org.example.cinemabackend.cinema.core.port.primary.CinemaUseCases;
import org.example.cinemabackend.shared.dto.ResponseList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cinemas")
@RequiredArgsConstructor
class CinemaController {

    private final CinemaUseCases cinemaUseCases;

    @GetMapping
    ResponseEntity<ResponseList<CinemaTableResponse>> getCinemas() {
        final var cinemas = cinemaUseCases.getCinemas();
        return ResponseEntity.ok(new ResponseList<>(cinemas));
    }

    @GetMapping("/{name}")
    ResponseEntity<CinemaResponse> getCinema(@PathVariable String name) {
        final var cinema = cinemaUseCases.getCinema(name);
        return ResponseEntity.ok(cinema);
    }

    @GetMapping("/names")
    ResponseEntity<ResponseList<String>> getCinemaNames() {
        final var cinemaNames = cinemaUseCases.getCinemaNames();
        return ResponseEntity.ok(new ResponseList<>(cinemaNames));
    }

    @PostMapping
    ResponseEntity<Void> createCinema(@RequestBody @Valid CreateCinemaRequest createCinemaRequest) {
        cinemaUseCases.createCinema(createCinemaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{name}")
    ResponseEntity<Void> deleteCinema(@PathVariable String name) {
        cinemaUseCases.deleteCinema(name);
        return ResponseEntity.noContent().build();
    }
}
