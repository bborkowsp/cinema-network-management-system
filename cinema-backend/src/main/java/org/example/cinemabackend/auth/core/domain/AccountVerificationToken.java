package org.example.cinemabackend.auth.core.domain;

import java.time.LocalDateTime;

public class AccountVerificationToken {
    private final String token;
    private final String email;
    private final LocalDateTime expiryDate;
    private Long id;

    public AccountVerificationToken(String token, String email, LocalDateTime expiryDate) {
        this.token = token;
        this.email = email;
        this.expiryDate = expiryDate;
    }

    public AccountVerificationToken(Long id, String token, String email, LocalDateTime expiryDate) {
        this.id = id;
        this.token = token;
        this.email = email;
        this.expiryDate = expiryDate;
    }


    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }
}
