package org.example.cinemabackend.user.application.dto.response;

import lombok.Builder;
import org.example.cinemabackend.user.core.domain.Role;

@Builder
public record CinemaNetworkManagerResponse(
        String firstName,
        String lastName,
        String email,
        Role role
) {
}
