package org.example.cinemabackend.movie.application.dto.response;

import lombok.Builder;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyResponse;
import org.example.cinemabackend.movie.core.domain.AgeRestriction;
import org.example.cinemabackend.movie.core.domain.Genre;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record MovieResponse(
        String title,
        String originalTitle,
        Integer duration,
        LocalDate releaseDate,
        String description,
        String poster,
        ProductionDetailsResponse productionDetails,
        SubtitleAndSoundOptionsResponse subtitleAndSoundOptions,
        AgeRestriction ageRestriction,
        VideoFileResponse trailer,
        Set<Genre> genres,
        Set<ProjectionTechnologyResponse> projectionTechnologies
) {
}
