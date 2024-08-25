package org.example.cinemabackend.user.application.adapter.primary;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.cinemabackend.user.application.dto.JwtDto;
import org.example.cinemabackend.user.application.dto.request.LoginUserRequest;
import org.example.cinemabackend.user.application.dto.request.RegisterUserRequest;
import org.example.cinemabackend.user.core.port.primary.AuthUseCases;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
class AuthController {

    private static final Logger LOGGER = LogManager.getLogger(AuthController.class);
    private final AuthUseCases authUseCases;

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody @Valid LoginUserRequest loginUserRequest) {
        final var jwt = authUseCases.login(loginUserRequest);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<JwtDto> register(@RequestBody @Valid RegisterUserRequest registerUserRequest) {
        authUseCases.register(registerUserRequest);
        LOGGER.info("User registered: " + registerUserRequest.email());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
