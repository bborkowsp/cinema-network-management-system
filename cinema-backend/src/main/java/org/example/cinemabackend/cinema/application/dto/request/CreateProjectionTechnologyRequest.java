package org.example.cinemabackend.cinema.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreateProjectionTechnologyRequest(
        @NotBlank @Size(max = 50) String technology,
        @NotBlank @Size(max = 200) String description
) {
}
