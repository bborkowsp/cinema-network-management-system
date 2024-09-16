package org.example.cinemabackend.cinema.application.dto.request.update;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UpdateScreeningRoomRequest(
        @NotBlank @Size(max = 50) String name,
        @NotNull @Valid UpdateSeatRequest[][] seats
) {
}
