package org.example.cinemabackend.user.application.adapter.primary;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.application.dto.JwtDto;
import org.example.cinemabackend.user.application.dto.LoginUserRequest;
import org.example.cinemabackend.user.application.dto.RegisterUserRequest;
import org.example.cinemabackend.user.core.port.primary.AuthUseCases;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class AuthController {

    private final AuthUseCases authUseCases;

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody @Valid LoginUserRequest loginUserRequest) {
        final var jwt = authUseCases.login(loginUserRequest);
        System.out.println("jwt: " + jwt);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterUserRequest registerUserRequest) {
        System.out.println("registerUserRequest: " + registerUserRequest);
        authUseCases.register(registerUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}