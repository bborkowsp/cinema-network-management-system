package org.example.cinemabackend.user.core.port.primary;

public interface EmailUseCases {
    void sendEmailToConfirmAccount(String to, String subject, String text);
}
