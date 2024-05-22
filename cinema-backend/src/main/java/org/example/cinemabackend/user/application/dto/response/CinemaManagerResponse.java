package org.example.cinemabackend.user.application.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CinemaManagerResponse(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email,
        String managedCinemaName
) {
}
