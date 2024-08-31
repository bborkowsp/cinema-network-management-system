package org.example.cinemabackend.user.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.application.dto.AccountVerificationTokenDto;
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
    public boolean verifyAccount(AccountVerificationTokenDto accountVerificationTokenDto) {
        return false;
    }

    @Override
    public String generateToken(User user) {
        final String token = UUID.randomUUID().toString();
        AccountVerificationToken accountVerificationToken = new AccountVerificationToken(token, user.getEmail(), EXPIRE_DATE);
        tokenRepository.save(accountVerificationToken);
        return token;
    }
}
