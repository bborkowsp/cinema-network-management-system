package org.example.cinemabackend.movie.application.dto.response;

import lombok.Builder;

@Builder
public record DescriptionResponse(
        String shortDescription,
        String longDescription
) {
}
