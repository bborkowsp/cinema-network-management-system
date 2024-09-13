package org.example.cinemabackend.cinema.application.adapter.primary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend._shared.dto.ResponseList;
import org.example.cinemabackend.cinema.application.dto.request.create.CreateScreeningRequest;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningDetailsResponse;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningResponse;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningUseCases;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/screenings")
@RequiredArgsConstructor
class ScreeningController {
    private final ScreeningUseCases screeningUseCases;

    @GetMapping("/repertory/{cinema}/{date}")
    ResponseEntity<ResponseList<ScreeningResponse>> getRepertoryAtSpecificDate(@PathVariable String cinema, @PathVariable LocalDate date) {
        final var screenings = screeningUseCases.getRepertoryAtSpecificDate(cinema, date);
        return ResponseEntity.ok(new ResponseList<>(screenings));
    }

    @GetMapping("/details/{title}/{date}")
    ResponseEntity<ScreeningDetailsResponse> getScreeningDetails(@PathVariable("title") String title, @PathVariable("date") LocalDate date) {
        final var screeningDetails = screeningUseCases.getScreeningDetails(title, date);
        return ResponseEntity.ok(screeningDetails);
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasAnyRole('CINEMA_MANAGER','ADMIN')")
    ResponseEntity<ResponseList<ScreeningResponse>> getScreenings(@PathVariable("email") String email) {
        final var screenings = screeningUseCases.getScreenings(email);
        return ResponseEntity.ok(new ResponseList<>(screenings));
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasAnyRole('CINEMA_MANAGER','ADMIN')")
    ResponseEntity<ScreeningResponse> getScreening(@PathVariable("id") Long id) {
        final var screening = screeningUseCases.getScreening(id);
        return ResponseEntity.ok(screening);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('CINEMA_MANAGER','ADMIN')")
    ResponseEntity<Void> createScreening(@RequestBody CreateScreeningRequest createScreeningRequest) {
        screeningUseCases.createScreening(createScreeningRequest);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('CINEMA_MANAGER','ADMIN')")
    ResponseEntity<ScreeningResponse> updateScreening(
            @PathVariable("id") Long id,
            @RequestBody CreateScreeningRequest createScreeningRequest
    ) {
        screeningUseCases.updateScreening(id, createScreeningRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('CINEMA_MANAGER','ADMIN')")
    ResponseEntity<Void> deleteScreening(@PathVariable("id") Long id) {
        screeningUseCases.deleteScreening(id);
        return ResponseEntity.noContent().build();
    }
}