package org.example.cinemabackend.cinema.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.Set;

@Builder
public record CreateScreeningRoomRequest(
        @NotBlank @Size(max = 50) String screeningRoomName,
        @NotNull Set<@NotNull CreatSeatRequest> seats,
        @NotNull Set<@NotNull CreateProjectionTechnologyRequest> supportedTechnologies
) {
}
