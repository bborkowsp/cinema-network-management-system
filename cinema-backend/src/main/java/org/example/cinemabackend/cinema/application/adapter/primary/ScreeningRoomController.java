package org.example.cinemabackend.cinema.application.adapter.primary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend._shared.dto.ResponseList;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningRoomUseCases;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/screening-rooms")
@RequiredArgsConstructor
public class ScreeningRoomController {
    private final ScreeningRoomUseCases screeningRoomUseCases;

    @GetMapping("/{email}")
    @PreAuthorize("hasAnyRole('CINEMA_MANAGER','ADMIN')")
    ResponseEntity<ResponseList<String>> getScreeningRoomsNames(@PathVariable String email) {
        final var screeningRoomsNames = screeningRoomUseCases.getScreeningRoomsNames(email);
        return ResponseEntity.ok(new ResponseList<>(screeningRoomsNames));
    }
}
