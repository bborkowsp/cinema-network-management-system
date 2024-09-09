package org.example.cinemabackend.auth.infrastructure.schema;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.example.cinemabackend.auth.core.domain.AccountVerificationToken;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountVerificationTokenSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "email")
    private String email;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    public static AccountVerificationTokenSchema fromAccountVerificationToken(AccountVerificationToken accountVerificationToken) {
        return AccountVerificationTokenSchema.builder()
                .token(accountVerificationToken.getToken())
                .email(accountVerificationToken.getEmail())
                .expiryDate(accountVerificationToken.getExpiryDate())
                .build();
    }

    public AccountVerificationToken toAccountVerificationToken() {
        return new AccountVerificationToken(
                this.id,
                this.token,
                this.email,
                this.expiryDate
        );
    }
}
