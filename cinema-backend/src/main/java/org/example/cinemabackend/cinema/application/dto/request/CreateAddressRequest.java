package org.example.cinemabackend.cinema.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateAddressRequest(
        @NotBlank String street,
        @NotBlank String buildingNumber,
        @NotBlank String city,
        @NotBlank String postalCode,
        @NotBlank String country
) {
}
