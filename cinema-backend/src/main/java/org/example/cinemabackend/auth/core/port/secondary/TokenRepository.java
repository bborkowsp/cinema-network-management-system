package org.example.cinemabackend.auth.core.port.secondary;

import org.example.cinemabackend.auth.core.domain.AccountVerificationToken;

public interface TokenRepository {
    void save(AccountVerificationToken accountVerificationToken);

    AccountVerificationToken findByToken(String token);
}
