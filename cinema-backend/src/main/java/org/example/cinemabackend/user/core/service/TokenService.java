package org.example.cinemabackend.user.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.core.domain.AccountVerificationToken;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.primary.TokenUseCases;
import org.example.cinemabackend.user.core.port.secondary.TokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService implements TokenUseCases {
    private static final LocalDateTime EXPIRE_DATE = LocalDateTime.now().plusDays(1);
    private final TokenRepository tokenRepository;

    @Override
    public String getEmailFromToken(String token) {
        AccountVerificationToken accountVerificationToken = tokenRepository.findByToken(token);
        return accountVerificationToken.getEmail();
    }

    @Override
    public void validateToken(String token) {
        AccountVerificationToken accountVerificationToken = tokenRepository.findByToken(token);
        if (accountVerificationToken == null) {
            throw new IllegalArgumentException("Invalid token");
        } else if (accountVerificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token expired");
        }
    }

    @Override
    public String generateToken(User user) {
        final String token = UUID.randomUUID().toString();
        AccountVerificationToken accountVerificationToken = new AccountVerificationToken(token, user.getEmail(), EXPIRE_DATE);
        tokenRepository.save(accountVerificationToken);
        return token;
    }
}
