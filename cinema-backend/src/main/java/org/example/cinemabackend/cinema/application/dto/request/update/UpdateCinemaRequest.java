package org.example.cinemabackend.cinema.application.dto.request.update;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.example.cinemabackend.user.application.dto.response.UserResponse;

import java.util.Set;

@Builder
public record UpdateCinemaRequest(
        @NotBlank @Size(max = 100) String name,
        @NotBlank @Size(max = 2000) String description,
        @NotNull @Valid UpdateAddressRequest address,
        @NotNull @Valid UpdateImageRequest image,
        @NotNull Set<@Valid @NotNull UpdateScreeningRoomRequest> screeningRooms,
        @NotNull Set<@Valid @NotNull UpdateContactDetailsRequest> contactDetails,
        @NotNull @Valid UserResponse cinemaManager
) {
}
