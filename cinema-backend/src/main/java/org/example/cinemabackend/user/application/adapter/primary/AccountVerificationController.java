package org.example.cinemabackend.user.application.adapter.primary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.core.domain.AccountVerificationToken;
import org.example.cinemabackend.user.core.port.primary.UserUseCases;
import org.example.cinemabackend.user.core.port.secondary.AccountVerificationTokenRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/v1/verify-account")
@RequiredArgsConstructor
public class AccountVerificationController {
    private final AccountVerificationTokenRepository accountVerificationTokenRepository;
    private final UserUseCases userUseCases;

    @GetMapping
    public ResponseEntity<?> verifyAccount(@RequestParam("token") String token) {
        AccountVerificationToken accountVerificationToken = accountVerificationTokenRepository.findByToken(token);
        if (accountVerificationToken == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("status", "failed", "reason", "invalid"));
        } else if (accountVerificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("status", "failed", "reason", "expired"));
        }
        userUseCases.verifyAccount(accountVerificationToken.getEmail());
        return ResponseEntity.ok(Map.of("status", "success"));
    }

}
