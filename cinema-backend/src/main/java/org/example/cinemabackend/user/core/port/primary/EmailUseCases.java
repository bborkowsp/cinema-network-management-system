package org.example.cinemabackend.user.core.port.primary;

public interface EmailUseCases {
    void sendEmailToResetPassword(String email, String resetPasswordUrl);

    void sendEmailToConfirmAccount(String to, String verificationUrl);
}
