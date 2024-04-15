package org.example.cinemabackend.movie.application.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record ProductionDetailsResponse(
        LocalDate worldPremiereDate,
        FilmMemberResponse director,
        Set<FilmMemberResponse> actors,
        Set<String> originalLanguages,
        Set<String> productionCountries
) {
}
