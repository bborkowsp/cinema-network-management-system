package org.example.cinemabackend.cinema.application.adapter.primary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningResponse;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningUseCases;
import org.example.cinemabackend.shared.dto.ResponseList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/screenings")
@RequiredArgsConstructor
class ScreeningController {

    private final ScreeningUseCases screeningUseCases;

    @GetMapping
    ResponseEntity<ResponseList<ScreeningResponse>> getScreenings() {
        final var cinemas = screeningUseCases.getScreenings();
        return ResponseEntity.ok(new ResponseList<>(cinemas));
    }

    @GetMapping("/{cinema-name}")
    ResponseEntity<ResponseList<ScreeningResponse>> getScreenings(@PathVariable("cinema-name") String cinemaName) {
        final var screenings = screeningUseCases.getScreenings(cinemaName);
        return ResponseEntity.ok(new ResponseList<>(screenings));
    }
}