package org.example.cinemabackend.cinema.application.dto.request.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.example.cinemabackend.user.application.dto.response.UserResponse;

import java.util.Set;

@Builder
public record CreateCinemaRequest(
        @NotBlank @Size(max = 100) String name,
        @NotBlank @Size(max = 2000) String description,
        @NotNull @Valid CreateAddressRequest address,
        @NotNull @Valid CreateImageRequest image,
        @NotNull Set<@Valid @NotNull CreateScreeningRoomRequest> screeningRooms,
        @NotNull Set<@Valid @NotNull CreateContactDetailsRequest> contactDetails,
        @NotNull @Valid UserResponse cinemaManager
) {
}
