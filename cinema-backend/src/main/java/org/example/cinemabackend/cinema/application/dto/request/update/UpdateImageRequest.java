package org.example.cinemabackend.cinema.application.dto.request.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdateImageRequest(
        @NotBlank String name,
        @NotBlank String type,
        @NotNull @NotEmpty byte[] data
) {
}
