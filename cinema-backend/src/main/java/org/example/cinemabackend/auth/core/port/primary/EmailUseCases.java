package org.example.cinemabackend.auth.core.port.primary;

import org.example.cinemabackend.ticketing.core.domain.Ticket;

public interface EmailUseCases {
    void sendTicketToUser(Ticket ticket);

    void sendEmailToResetPassword(String email, String resetPasswordUrl);

    void sendEmailToConfirmAccount(String to, String verificationUrl);
}
