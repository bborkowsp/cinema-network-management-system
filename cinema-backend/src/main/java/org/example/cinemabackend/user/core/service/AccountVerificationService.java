package org.example.cinemabackend.user.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.application.dto.AccountVerificationTokenDto;
import org.example.cinemabackend.user.core.domain.AccountVerificationToken;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.primary.AccountVerificationUseCases;
import org.example.cinemabackend.user.core.port.secondary.AccountVerificationTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountVerificationService implements AccountVerificationUseCases {

    private final AccountVerificationTokenRepository accountVerificationTokenRepository;


    @Override
    public boolean verifyAccount(AccountVerificationTokenDto accountVerificationTokenDto) {
        return false;
    }

    @Override
    public String generateAccountVerificationToken(User user) {
        final String token = UUID.randomUUID().toString();
        final LocalDateTime expiryDate = LocalDateTime.now().plusSeconds(30);
        AccountVerificationToken accountVerificationToken = new AccountVerificationToken(token, user.getEmail(), expiryDate);
        accountVerificationTokenRepository.save(accountVerificationToken);
        return token;
    }
}
