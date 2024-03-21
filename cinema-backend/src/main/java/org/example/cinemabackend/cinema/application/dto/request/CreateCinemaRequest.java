package org.example.cinemabackend.cinema.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Set;

@Builder
public record CreateCinemaRequest(
        @NotBlank String name,
        @NotBlank String description,
        @NotNull CreateAddressRequest address,
        @NotNull CreateImageRequest image,
        @NotNull Set<@NotNull CreateScreeningRoomRequest> screeningRooms,
        @NotNull Set<@NotNull CreateContactDetailsRequest> contactDetails
) {
}
