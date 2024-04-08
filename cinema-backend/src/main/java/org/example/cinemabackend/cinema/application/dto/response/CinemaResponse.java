package org.example.cinemabackend.cinema.application.dto.response;

import lombok.Builder;

import java.util.Set;

@Builder
public record CinemaResponse(
        String name,
        String description,
        AddressResponse address,
        ImageResponse image,
        Set<ContactDetailsResponse> contactDetails,
        CinemaManagerResponse cinemaManager
) {
}
