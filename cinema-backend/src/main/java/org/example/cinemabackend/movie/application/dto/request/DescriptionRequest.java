package org.example.cinemabackend.movie.application.dto.request;

public record DescriptionRequest(
        String shortDescription,
        String longDescription
) {
}
