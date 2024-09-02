package org.example.cinemabackend.user.application.adapter.primary;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.application.dto.request.CreateUserRequest;
import org.example.cinemabackend.user.application.dto.request.UpdateUserRequest;
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
    private static final String CINEMA_NETWORK_MANAGERS_ENDPOINT_PREFIX = "/cinema-network-managers";
    private final UserUseCases userUseCases;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Page<UserResponse>> getUsers(Pageable pageable) {
        final var users = userUseCases.getUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<UserResponse> getUser(@PathVariable String email) {
        final var cinemaManager = userUseCases.getUser(email);
        return ResponseEntity.ok(cinemaManager);
    }

    @PostMapping(CINEMA_NETWORK_MANAGERS_ENDPOINT_PREFIX)
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        userUseCases.createUser(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PatchMapping(CINEMA_NETWORK_MANAGERS_ENDPOINT_PREFIX + "/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Void> updateCinemaNetworkManager(
            @PathVariable String email,
            @RequestBody @Valid UpdateUserRequest updateCinemaManagerRequest
    ) {
        userUseCases.updateCinemaNetworkManager(email, updateCinemaManagerRequest);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{email}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userUseCases.deleteUser(email);
        return ResponseEntity.noContent().build();
    }
}
