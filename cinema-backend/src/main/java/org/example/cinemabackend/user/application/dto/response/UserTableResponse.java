package org.example.cinemabackend.user.application.dto.response;

import lombok.Builder;

@Builder
public record UserTableResponse(
        String firstName,
        String lastName,
        String email
) {
}