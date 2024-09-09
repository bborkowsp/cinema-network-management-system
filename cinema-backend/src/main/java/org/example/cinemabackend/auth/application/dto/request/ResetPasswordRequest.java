package org.example.cinemabackend.auth.application.dto.request;

public record ResetPasswordRequest(
        String newPassword,
        String token
) {
}
