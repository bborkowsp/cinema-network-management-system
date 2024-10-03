package org.example.cinemabackend.user.infrastructure.schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.cinemabackend.user.core.domain.Customer;
import org.example.cinemabackend.user.core.domain.Role;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerSchema extends UserSchema {

    @Column(nullable = false)
    private Boolean isAccountVerified = false;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public CustomerSchema(Long id, String firstName, String lastName, String email, String passwordHash, Role role, Boolean isAccountVerified, LocalDateTime createdAt) {
        super(id, firstName, lastName, email, passwordHash, role);
        this.isAccountVerified = isAccountVerified;
        this.createdAt = createdAt;
    }

    public static CustomerSchema fromCustomer(Customer customer) {
        return new CustomerSchema(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPasswordHash(),
                customer.getRole(),
                customer.isAccountVerified(),
                customer.getCreatedAt()
        );
    }

    public static Customer toCustomer(CustomerSchema customerSchema) {
        return new Customer(
                customerSchema.getId(),
                customerSchema.getFirstName(),
                customerSchema.getLastName(),
                customerSchema.getEmail(),
                customerSchema.getPasswordHash(),
                customerSchema.getRole(),
                customerSchema.isAccountVerified(),
                customerSchema.getCreatedAt()
        );
    }

    public boolean isAccountVerified() {
        return isAccountVerified;
    }

    public void setAccountVerified(boolean accountVerified) {
        isAccountVerified = accountVerified;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
