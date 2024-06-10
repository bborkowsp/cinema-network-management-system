package org.example.cinemabackend.cinema.application.adapter.primary;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.create.CreateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.response.CinemaResponse;
import org.example.cinemabackend.cinema.application.dto.response.CinemaTableResponse;
import org.example.cinemabackend.cinema.core.port.primary.CinemaUseCases;
import org.example.cinemabackend.shared.dto.ResponseList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cinemas")
@RequiredArgsConstructor
class CinemaController {

    private final CinemaUseCases cinemaUseCases;

    @GetMapping
    @PreAuthorize("hasAuthority('CINEMA_NETWORK_MANAGER')")
    ResponseEntity<ResponseList<CinemaTableResponse>> getCinemas() {
        final var cinemas = cinemaUseCases.getCinemas();
        return ResponseEntity.ok(new ResponseList<>(cinemas));
    }

    @GetMapping("/names")
    @PreAuthorize("hasAuthority('CINEMA_NETWORK_MANAGER')")
    ResponseEntity<ResponseList<String>> getCinemaNames() {
        final var cinemaNames = cinemaUseCases.getCinemaNames();
        return ResponseEntity.ok(new ResponseList<>(cinemaNames));
    }

    @GetMapping("/screening-rooms/{email}")
    ResponseEntity<ResponseList<String>> getScreeningRoomsNames(@PathVariable String email) {
        final var screeningRoomsNames = cinemaUseCases.getScreeningRoomsNames(email);
        return ResponseEntity.ok(new ResponseList<>(screeningRoomsNames));
    }

    @GetMapping("/{name}")
    ResponseEntity<CinemaResponse> getCinema(@PathVariable String name) {
        final var cinema = cinemaUseCases.getCinema(name);
        return ResponseEntity.ok(cinema);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CINEMA_NETWORK_MANAGER')")
    ResponseEntity<Void> createCinema(@RequestBody @Valid CreateCinemaRequest createCinemaRequest) {
        cinemaUseCases.createCinema(createCinemaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{name}")
    @PreAuthorize("hasAuthority('CINEMA_NETWORK_MANAGER')")
    ResponseEntity<Void> updateCinema(@PathVariable String name, @RequestBody @Valid UpdateCinemaRequest updateCinemaRequest) {
        cinemaUseCases.updateCinema(name, updateCinemaRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{name}")
    @PreAuthorize("hasAuthority('CINEMA_NETWORK_MANAGER')")
    ResponseEntity<Void> deleteCinema(@PathVariable String name) {
        cinemaUseCases.deleteCinema(name);
        return ResponseEntity.noContent().build();
    }
}
