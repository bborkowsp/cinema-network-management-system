package org.example.cinemabackend.user.application.adapter.primary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.shared.dto.ResponseList;
import org.example.cinemabackend.user.application.dto.response.UserTableResponse;
import org.example.cinemabackend.user.core.port.primary.UserUseCases;
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
    ResponseEntity<ResponseList<UserTableResponse>> getUsers() {
        final var users = userUseCases.getUsers();
        return ResponseEntity.ok(new ResponseList<>(users));
    }
}

