package org.example.cinemabackend.cinema.application.dto.response;

import lombok.Builder;

@Builder
public record ContactDetailsResponse(
        String department,
        ContactTypeResponse contactType
) {
}
