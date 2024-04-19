package org.example.cinemabackend.user.application.dto;

import lombok.Builder;

@Builder
public record UserResponse(
        String firstName,
        String lastName,
        String email
) {
}
