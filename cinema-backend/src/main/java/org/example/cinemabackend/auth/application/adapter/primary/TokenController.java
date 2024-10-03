package org.example.cinemabackend.auth.application.adapter.primary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.auth.core.domain.AccountVerificationToken;
import org.example.cinemabackend.auth.core.port.primary.CustomerAuthUseCases;
import org.example.cinemabackend.auth.core.port.secondary.TokenRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class TokenController {
    private final TokenRepository tokenRepository;
    private final CustomerAuthUseCases customerAuthUseCases;

    @GetMapping("/verify-account")
    public ResponseEntity<?> verifyAccount(@RequestParam("token") String token) {
        AccountVerificationToken accountVerificationToken = tokenRepository.findByToken(token);
        if (accountVerificationToken == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("status", "failed",
                            "reason", "invalid")
            );
        } else if (accountVerificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("status", "failed",
                            "reason", "expired")
            );
        }
        customerAuthUseCases.verifyCustomerAccount(accountVerificationToken.getEmail());
        return ResponseEntity.ok(Map.of("status", "success"));
    }
}
