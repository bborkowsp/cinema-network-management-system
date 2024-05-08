package org.example.cinemabackend.movie.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record FilmMemberRequest(
        @NotBlank String firstName,
        @NotBlank String lastName
) {
}
