package org.example.cinemabackend.movie.application.dto.response;

import org.example.cinemabackend.movie.core.domain.Language;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;

public record MovieVariantResponse(
        ProjectionTechnology projectionTechnology,
        Language language
) {
}
