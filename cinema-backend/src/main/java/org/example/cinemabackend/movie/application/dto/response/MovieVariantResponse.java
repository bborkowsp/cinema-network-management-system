package org.example.cinemabackend.movie.application.dto.response;

import org.example.cinemabackend.movie.core.domain.Language;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnologyEnum;

public record MovieVariantResponse(
        ProjectionTechnologyEnum projectionTechnologyEnum,
        Language language
) {
}
