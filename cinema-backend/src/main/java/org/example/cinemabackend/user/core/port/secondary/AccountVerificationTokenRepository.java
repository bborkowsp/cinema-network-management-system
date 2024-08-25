package org.example.cinemabackend.user.core.port.secondary;

import org.example.cinemabackend.user.core.domain.AccountVerificationToken;

public interface AccountVerificationTokenRepository {
    void save(AccountVerificationToken accountVerificationToken);

    AccountVerificationToken findByToken(String token);
}
