package org.example.cinemabackend.cinema.application.dto.request.update;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UpdateAddressRequest(
        @NotBlank String streetAndBuildingNumber,
        @NotBlank String city,
        @NotBlank String postalCode,
        @NotBlank String country
) {
}
