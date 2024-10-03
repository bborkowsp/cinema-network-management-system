package org.example.cinemabackend.user.core.domain;

import java.time.LocalDateTime;

public class Customer extends User {
    private boolean isAccountVerified = false;
    private LocalDateTime createdAt;

    public Customer(Long id, String firstName, String lastName, String email, String passwordHash, Role role, boolean isAccountVerified, LocalDateTime createdAt) {
        super(id, firstName, lastName, email, passwordHash, role);
        this.isAccountVerified = isAccountVerified;
        this.createdAt = createdAt;
    }

    public Customer(String firstName, String lastName, String email, String passwordHash, Role role, boolean isAccountVerified, LocalDateTime createdAt) {
        super(firstName, lastName, email, passwordHash, role);
        this.isAccountVerified = isAccountVerified;
        this.createdAt = createdAt;
    }

    public boolean isAccountVerified() {
        return isAccountVerified;
    }

    public void setIsAccountVerified(boolean isAccountVerified) {
        this.isAccountVerified = isAccountVerified;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
