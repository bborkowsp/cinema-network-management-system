package org.example.cinemabackend.user.application.adapter.primary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.core.domain.AccountVerificationToken;
import org.example.cinemabackend.user.core.port.primary.UserUseCases;
import org.example.cinemabackend.user.core.port.secondary.AccountVerificationTokenRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/verify-account")
@RequiredArgsConstructor
public class AccountVerificationController {
    private static final String ACCOUNT_VERIFICATION_TOKEN_EXPIRED = "Account verification token has expired";
    private static final String ACCOUNT_VERIFICATION_TOKEN_INVALID = "Invalid account verification token";
    private final AccountVerificationTokenRepository accountVerificationTokenRepository;
    private final UserUseCases userUseCases;

    @GetMapping
    public ResponseEntity<String> verifyAccount(@RequestParam("token") String token) {
        AccountVerificationToken accountVerificationToken = accountVerificationTokenRepository.findByToken(token);
        if (accountVerificationToken == null) {
            throw new IllegalArgumentException(ACCOUNT_VERIFICATION_TOKEN_INVALID);
        } else if (accountVerificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(ACCOUNT_VERIFICATION_TOKEN_EXPIRED);
        }
        userUseCases.verifyAccount(accountVerificationToken.getEmail());
        return ResponseEntity.ok("Account verified");
    }

}
