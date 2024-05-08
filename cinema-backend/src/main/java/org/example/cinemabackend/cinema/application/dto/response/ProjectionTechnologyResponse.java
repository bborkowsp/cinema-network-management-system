package org.example.cinemabackend.cinema.application.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ProjectionTechnologyResponse(
        @NotBlank @Size(max = 50) String technology,
        String description
) {
}
