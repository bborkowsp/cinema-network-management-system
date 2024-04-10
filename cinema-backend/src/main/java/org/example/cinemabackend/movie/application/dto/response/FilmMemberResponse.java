package org.example.cinemabackend.movie.application.dto.response;

import lombok.Builder;

@Builder
public record FilmMemberResponse(
        String firstName,
        String lastName
) {
}
