package org.example.cinemabackend.user.infrastructure.adapter.secondary;

import org.example.cinemabackend.user.infrastructure.scheme.AccountVerificationTokenSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountVerificationTokenJpaRepository extends JpaRepository<AccountVerificationTokenSchema, Long> {
    AccountVerificationTokenSchema findByToken(String token);
}
