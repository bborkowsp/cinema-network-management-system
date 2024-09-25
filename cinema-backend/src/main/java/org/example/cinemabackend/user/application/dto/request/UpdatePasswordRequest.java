package org.example.cinemabackend.user.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePasswordRequest(
        @NotBlank @Size(max = 255) String currentPassword,
        @NotBlank @Size(max = 255) String newPassword
) {
}
