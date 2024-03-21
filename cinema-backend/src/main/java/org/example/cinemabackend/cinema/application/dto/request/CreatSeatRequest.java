package org.example.cinemabackend.cinema.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.example.cinemabackend.cinema.core.domain.SeatType;
import org.example.cinemabackend.cinema.core.domain.SeatZone;

@Builder
public record CreatSeatRequest(
        @NotBlank @Size(max = 2) String seatNumber,
        @NotBlank @Size(max = 2) String seatLetter,
        @NotNull SeatZone seatZone,
        @NotNull SeatType seatType
) {
}
