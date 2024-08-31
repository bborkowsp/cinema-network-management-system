package org.example.cinemabackend.user.application.dto.request;

public record ResetPasswordRequest(
        String newPassword,
        String token
) {
}
