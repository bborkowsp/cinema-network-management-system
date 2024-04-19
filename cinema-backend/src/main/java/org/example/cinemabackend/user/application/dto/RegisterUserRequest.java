package org.example.cinemabackend.user.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.cinemabackend.user.core.domain.Role;

public record RegisterUserRequest(
        @NotBlank @Size(max = 255) String firstName,
        @NotBlank @Size(max = 255) String lastName,
        @NotBlank @Size(max = 255) @Email String email,
        @NotBlank @Size(max = 255) String password,
        @NotNull Role role
) {
}
