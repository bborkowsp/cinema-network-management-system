package org.example.cinemabackend.auth.core.port.primary;

import org.example.cinemabackend.user.core.domain.User;

public interface TokenUseCases {
    String getEmailFromToken(String token);

    void validateToken(String token);

    String generateToken(User user);
}
