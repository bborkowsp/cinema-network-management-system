package org.example.cinemabackend.user.application.adapter.primary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.shared.dto.ResponseList;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerResponse;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerTableResponse;
import org.example.cinemabackend.user.core.port.primary.UserUseCases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserUseCases userUseCases;

    @GetMapping
    ResponseEntity<Page<CinemaManagerTableResponse>> getCinemaManagers(Pageable pageable) {
        final var cinemaManagers = userUseCases.getCinemaManagers(pageable);
        return ResponseEntity.ok(cinemaManagers);
    }

    @GetMapping("/cinema-managers")
    ResponseEntity<ResponseList<CinemaManagerResponse>> getCinemaManagers() {
        final var cinemaManagers = userUseCases.getCinemaManagers();
        return ResponseEntity.ok(new ResponseList<>(cinemaManagers));
    }
}

