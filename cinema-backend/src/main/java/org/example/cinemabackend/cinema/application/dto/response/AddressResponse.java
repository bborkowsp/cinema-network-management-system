package org.example.cinemabackend.cinema.application.dto.response;

import lombok.Builder;

@Builder
public record AddressResponse(
        String street,
        String buildingNumber,
        String city,
        String postalCode,
        String country
) {
}
