package org.example.cinemabackend.cinema.application.dto.response;

import lombok.Builder;

@Builder
public record ContactTypeResponse(
        String phoneNumber,
        String email
) {
}
