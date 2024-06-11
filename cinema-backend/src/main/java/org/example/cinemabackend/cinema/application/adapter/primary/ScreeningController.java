package org.example.cinemabackend.cinema.application.adapter.primary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.ScreeningRequest;
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

    @GetMapping("/{email}")
    ResponseEntity<ResponseList<ScreeningResponse>> getScreenings(@PathVariable("email") String email) {
        final var screenings = screeningUseCases.getScreenings(email);
        return ResponseEntity.ok(new ResponseList<>(screenings));
    }

    @GetMapping("/id/{id}")
    ResponseEntity<ScreeningResponse> getScreening(@PathVariable("id") Long id) {
        final var screening = screeningUseCases.getScreening(id);
        return ResponseEntity.ok(screening);
    }

    @PostMapping
    ResponseEntity<Void> createScreening(@RequestBody ScreeningRequest screening) {
        screeningUseCases.createScreening(screening);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<ScreeningResponse> updateScreening(
            @PathVariable("id") Long id,
            @RequestBody ScreeningRequest screening
    ) {
        screeningUseCases.updateScreening(id, screening);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteScreening(@PathVariable("id") Long id) {
        screeningUseCases.deleteScreening(id);
        return ResponseEntity.noContent().build();
    }

}