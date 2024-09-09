package org.example.cinemabackend.auth.application.adapter.primary;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.cinemabackend.auth.application.dto.JwtDto;
import org.example.cinemabackend.auth.application.dto.request.LoginUserRequest;
import org.example.cinemabackend.auth.application.dto.request.RegisterUserRequest;
import org.example.cinemabackend.auth.application.dto.request.ResetPasswordRequest;
import org.example.cinemabackend.auth.core.port.primary.AuthUseCases;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
        LOGGER.info("User: " + registerUserRequest.email() + "requested for registration.");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/reset-password-request")
    public ResponseEntity<Void> resetPassword(@RequestParam("email") String email) {
        authUseCases.processRequestForPasswordReset(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody @Valid ResetPasswordRequest resetPasswordRequest) {
        authUseCases.resetPassword(resetPasswordRequest);
        return ResponseEntity.ok().build();
    }
}
