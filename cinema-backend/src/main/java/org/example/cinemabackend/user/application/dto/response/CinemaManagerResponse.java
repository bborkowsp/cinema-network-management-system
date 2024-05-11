package org.example.cinemabackend.user.application.dto.response;

import lombok.Builder;

@Builder
public record CinemaManagerResponse(
        String firstName,
        String lastName,
        String email
) {
}
