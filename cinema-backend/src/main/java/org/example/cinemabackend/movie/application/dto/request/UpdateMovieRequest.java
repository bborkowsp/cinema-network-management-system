package org.example.cinemabackend.movie.application.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateImageRequest;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyResponse;
import org.example.cinemabackend.movie.core.domain.AgeRestriction;
import org.example.cinemabackend.movie.core.domain.Genre;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record UpdateMovieRequest(
        @NotBlank String title,
        @NotBlank String originalTitle,
        @NotNull Double duration,
        @NotNull LocalDate releaseDate,
        @NotBlank String description,
        @NotNull @Valid ProductionDetailsRequest productionDetails,
        @NotNull @Valid SubtitleAndSoundOptionsRequest subtitleAndSoundOptions,
        @NotNull AgeRestriction ageRestriction,
        @NotNull @Valid UpdateImageRequest image,
        @NotNull @Valid VideoFileRequest trailer,
        @NotNull Set<Genre> genres,
        @NotNull Set<@NotNull @Valid ProjectionTechnologyResponse> projectionTechnologies
) {
}