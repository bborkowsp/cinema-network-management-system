package org.example.cinemabackend.user.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.core.domain.AccountVerificationToken;
import org.example.cinemabackend.user.core.port.secondary.AccountVerificationTokenRepository;
import org.example.cinemabackend.user.infrastructure.scheme.AccountVerificationTokenSchema;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountVerificationTokenGateway implements AccountVerificationTokenRepository {

    private final AccountVerificationTokenJpaRepository accountVerificationTokenJpaRepository;


    @Override
    public void save(AccountVerificationToken accountVerificationToken) {
        final var accountVerificationTokenSchema = AccountVerificationTokenSchema.fromAccountVerificationToken(accountVerificationToken);
        this.accountVerificationTokenJpaRepository.save(accountVerificationTokenSchema);
    }

    @Override
    public AccountVerificationToken findByToken(String token) {
        return this.accountVerificationTokenJpaRepository.findByToken(token).toAccountVerificationToken();
    }
}
