package org.example.cinemabackend.movie.application.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record ProductionDetailsRequest(
        @NotNull LocalDate worldPremiereDate,
        @NotNull @Valid FilmMemberRequest director,
        @NotNull Set<@NotNull @Valid FilmMemberRequest> actors,
        @NotNull Set<String> originalLanguages,
        @NotNull Set<String> productionCountries
) {
}
