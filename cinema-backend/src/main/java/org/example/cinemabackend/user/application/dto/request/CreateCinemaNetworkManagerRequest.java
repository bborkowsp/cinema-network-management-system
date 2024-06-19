package org.example.cinemabackend.user.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCinemaNetworkManagerRequest(
        @NotBlank @Size(max = 255) String firstName,
        @NotBlank @Size(max = 255) String lastName,
        @NotBlank @Email @Size(max = 255) String email,
        @NotBlank @Size(max = 255) String password
) {
}
