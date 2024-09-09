package org.example.cinemabackend.auth.application.dto.request;

import org.example.cinemabackend.user.core.domain.Role;

public record RegisterUserRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        Role role
) {
}
