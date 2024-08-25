package org.example.cinemabackend.user.core.port.primary;

import org.example.cinemabackend.user.application.dto.AccountVerificationTokenDto;
import org.example.cinemabackend.user.core.domain.User;

public interface AccountVerificationUseCases {
    boolean verifyAccount(AccountVerificationTokenDto accountVerificationTokenDto);

    String generateAccountVerificationToken(User user);
}
