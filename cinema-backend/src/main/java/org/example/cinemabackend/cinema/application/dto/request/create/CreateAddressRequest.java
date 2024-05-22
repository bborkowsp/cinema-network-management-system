package org.example.cinemabackend.cinema.application.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateAddressRequest(
        @NotBlank String streetAndBuildingNumber,
        @NotBlank String city,
        @NotBlank String postalCode,
        @NotBlank String country
) {
}
