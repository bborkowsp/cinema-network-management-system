package org.example.cinemabackend.movie.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record VideoFileRequest(
        @NotBlank String url
) {
}
