package org.example.cinemabackend.auth.application.dto;

public record AccountVerificationTokenDto(
        String token,
        String email,
        String expirationDate
) {
}
