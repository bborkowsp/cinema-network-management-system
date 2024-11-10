package org.example.cinemabackend.ticketing.application.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import org.example.cinemabackend.cinema.application.dto.response.SeatResponse;

import java.util.List;

@Builder
public record BuyTicketRequest(
        @NotNull Long screeningId,
        @NotEmpty List<@NotBlank SeatResponse> selectedSeats,
        @NotBlank @Size(min = 1, max = 50) String firstName,
        @NotBlank @Size(min = 1, max = 50) String lastName,
        @NotBlank @Email String email,
        @NotBlank String paymentMethod
) {
}
