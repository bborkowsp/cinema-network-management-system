package org.example.cinemabackend.user.application.adapter.primary;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.application.dto.request.CreateCinemaManagerRequest;
import org.example.cinemabackend.user.application.dto.request.CreateCinemaNetworkManagerRequest;
import org.example.cinemabackend.user.application.dto.request.UpdateCinemaManagerRequest;
import org.example.cinemabackend.user.application.dto.request.UpdateCinemaNetworkManagerRequest;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerResponse;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerTableResponse;
import org.example.cinemabackend.user.application.dto.response.UserResponse;
import org.example.cinemabackend.user.core.port.primary.UserUseCases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private static final String CINEMA_MANAGERS_ENDPOINT_PREFIX = "/cinema-managers";
    private static final String CINEMA_NETWORK_MANAGERS_ENDPOINT_PREFIX = "/cinema-network-managers";
    private final UserUseCases userUseCases;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Page<UserResponse>> getUsers(Pageable pageable) {
        final var users = userUseCases.getUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping(CINEMA_MANAGERS_ENDPOINT_PREFIX)
    @PreAuthorize("hasAnyRole('CINEMA_NETWORK_MANAGER','ADMIN')")
    ResponseEntity<Page<CinemaManagerTableResponse>> getCinemaManagers(Pageable pageable) {
        final var cinemaManagers = userUseCases.getCinemaManagers(pageable);
        return ResponseEntity.ok(cinemaManagers);
    }


    @GetMapping(CINEMA_MANAGERS_ENDPOINT_PREFIX + "/{email}")
    @PreAuthorize("hasAnyRole('CINEMA_NETWORK_MANAGER','ADMIN')")
    ResponseEntity<CinemaManagerResponse> getCinemaManager(@PathVariable String email) {
        final var cinemaManager = userUseCases.getCinemaManager(email);
        return ResponseEntity.ok(cinemaManager);
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<UserResponse> getUser(@PathVariable String email) {
        final var cinemaManager = userUseCases.getUser(email);
        return ResponseEntity.ok(cinemaManager);
    }

    @PostMapping(CINEMA_MANAGERS_ENDPOINT_PREFIX)
    @PreAuthorize("hasAnyRole('CINEMA_NETWORK_MANAGER','ADMIN')")
    ResponseEntity<Void> createCinemaManager(@RequestBody @Valid CreateCinemaManagerRequest createCinemaManagerRequest) {
        userUseCases.createCinemaManager(createCinemaManagerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(CINEMA_NETWORK_MANAGERS_ENDPOINT_PREFIX)
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Void> createCinemaNetworkManager(@RequestBody @Valid CreateCinemaNetworkManagerRequest createCinemaManagerRequest) {
        userUseCases.createCinemaNetworkManager(createCinemaManagerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PatchMapping(CINEMA_MANAGERS_ENDPOINT_PREFIX + "/{email}")
    @PreAuthorize("hasAnyRole('CINEMA_NETWORK_MANAGER','ADMIN')")
    ResponseEntity<Void> updateCinemaManager(
            @PathVariable String email,
            @RequestBody @Valid UpdateCinemaManagerRequest updateCinemaManagerRequest
    ) {
        userUseCases.updateCinemaManager(email, updateCinemaManagerRequest);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(CINEMA_NETWORK_MANAGERS_ENDPOINT_PREFIX + "/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Void> updateCinemaNetworkManager(
            @PathVariable String email,
            @RequestBody @Valid UpdateCinemaNetworkManagerRequest updateCinemaManagerRequest
    ) {
        userUseCases.updateCinemaNetworkManager(email, updateCinemaManagerRequest);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping(CINEMA_MANAGERS_ENDPOINT_PREFIX + "/{email}")
    @PreAuthorize("hasAnyRole('CINEMA_NETWORK_MANAGER','ADMIN')")
    ResponseEntity<Void> deleteCinemaManager(@PathVariable String email) {
        userUseCases.deleteCinemaManager(email);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{email}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userUseCases.deleteUser(email);
        return ResponseEntity.noContent().build();
    }
}
