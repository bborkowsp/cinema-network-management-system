package org.example.cinemabackend.auth.core.port.primary;

import org.example.cinemabackend.user.core.domain.User;

public interface JwtUseCases {
    String createAndEncodeJwt(User email);

    String createRefreshToken(String jwt);
}
