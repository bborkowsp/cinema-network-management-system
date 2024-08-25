package org.example.cinemabackend.user.application.dto;

public record AccountVerificationTokenDto(
        String token,
        String email,
        String expirationDate
) {
}
