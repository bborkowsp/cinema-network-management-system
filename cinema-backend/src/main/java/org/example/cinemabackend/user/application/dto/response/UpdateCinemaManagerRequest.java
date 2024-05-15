package org.example.cinemabackend.user.application.dto.response;

public record UpdateCinemaManagerRequest(
        String firstName,
        String lastName,
        String email,
        String managedCinemaName
) {
}
