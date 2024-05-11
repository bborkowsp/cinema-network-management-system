package org.example.cinemabackend.cinema.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyNameResponse;

import java.util.Set;

@Builder
public record CreateScreeningRoomRequest(
        @NotBlank @Size(max = 50) String name,
        @NotNull CreatSeatRequest[][] seats,
        @NotNull Set<@NotNull ProjectionTechnologyNameResponse> supportedTechnologies
) {
}
