package org.example.cinemabackend.cinema.application.adapter.primary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.UpdateScreeningRequest;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningResponse;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningUseCases;
import org.example.cinemabackend.shared.dto.ResponseList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{email}")
    ResponseEntity<ResponseList<ScreeningResponse>> getScreenings(@PathVariable("email") String email) {
        final var screenings = screeningUseCases.getScreenings(email);
        return ResponseEntity.ok(new ResponseList<>(screenings));
    }

    @PatchMapping("/{id}")
    ResponseEntity<ScreeningResponse> updateScreening(@PathVariable("id") Long id, @RequestBody UpdateScreeningRequest screening) {
        screeningUseCases.updateScreening(id, screening);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteScreening(@PathVariable("id") Long id) {
        screeningUseCases.deleteScreening(id);
        return ResponseEntity.noContent().build();
    }

}