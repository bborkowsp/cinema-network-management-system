package org.example.cinemabackend.movie.application.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.example.cinemabackend.movie.application.dto.response.MovieVariantResponse;
import org.example.cinemabackend.movie.core.domain.AgeRestriction;
import org.example.cinemabackend.movie.core.domain.Genre;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record CreateMovieRequest(
        @NotBlank String title,
        @NotBlank String originalTitle,
        @NotNull Integer duration,
        @NotNull LocalDate releaseDate,
        @NotBlank String description,
        @NotNull @Valid ProductionDetailsRequest productionDetails,
        @NotNull AgeRestriction ageRestriction,
        @NotNull String trailer,
        @NotNull Set<Genre> genres,
        @NotNull Set<@NotNull @Valid MovieVariantResponse> movieVariants
) {
}
