package org.example.cinemabackend.user.application.adapter.primary;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.shared.dto.ResponseList;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerResponse;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerTableResponse;
import org.example.cinemabackend.user.application.dto.response.UpdateCinemaManagerRequest;
import org.example.cinemabackend.user.core.port.primary.UserUseCases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserUseCases userUseCases;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('CINEMA_NETWORK_MANAGER','ADMIN')")
    ResponseEntity<Page<CinemaManagerTableResponse>> getCinemaManagers(Pageable pageable) {
        final var cinemaManagers = userUseCases.getCinemaManagers(pageable);
        return ResponseEntity.ok(cinemaManagers);
    }

    @GetMapping("/cinema-managers")
    @PreAuthorize("hasAnyAuthority('CINEMA_NETWORK_MANAGER','ADMIN')")
    ResponseEntity<ResponseList<CinemaManagerResponse>> getCinemaManagers() {
        final var cinemaManagers = userUseCases.getCinemaManagers();
        return ResponseEntity.ok(new ResponseList<>(cinemaManagers));
    }

    @GetMapping("/cinema-managers/{email}")
    @PreAuthorize("hasAnyAuthority('CINEMA_NETWORK_MANAGER','ADMIN')")
    ResponseEntity<CinemaManagerResponse> getCinema(@PathVariable String email) {
        final var cinemaManager = userUseCases.getCinemaManager(email);
        return ResponseEntity.ok(cinemaManager);
    }


    @PatchMapping("/cinema-managers/{email}")
    @PreAuthorize("hasAnyAuthority('CINEMA_NETWORK_MANAGER','ADMIN')")
    ResponseEntity<Void> updateProjectionTechnology(
            @PathVariable String email,
            @RequestBody @Valid UpdateCinemaManagerRequest updateCinemaManagerRequest
    ) {
        userUseCases.updateCinemaManager(email, updateCinemaManagerRequest);
        return ResponseEntity.noContent().build();
    }
}
