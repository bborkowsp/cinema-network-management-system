package org.example.cinemabackend.cinema.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateProjectionTechnologyRequest(
        @NotBlank @Size(max = 50) String technology,
        @NotBlank @Size(max = 200) String description
) {
}
