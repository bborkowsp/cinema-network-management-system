package org.example.cinemabackend.cinema.application.dto.request.update;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.example.cinemabackend.cinema.application.dto.response.ProjectionTechnologyNameResponse;

import java.util.Set;

@Builder
public record UpdateScreeningRoomRequest(
        //       @NotNull Long id,
        @NotBlank @Size(max = 50) String name,
        @NotNull @Valid UpdateSeatRequest[][] seats,
        @NotNull Set<@NotNull @Valid ProjectionTechnologyNameResponse> supportedTechnologies
) {
}
