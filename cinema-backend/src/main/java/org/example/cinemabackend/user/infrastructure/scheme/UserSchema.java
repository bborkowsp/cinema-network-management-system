package org.example.cinemabackend.user.infrastructure.scheme;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.example.cinemabackend.user.core.domain.Gender;
import org.example.cinemabackend.user.infrastructure.config.AbstractEntitySchema;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
public abstract class UserSchema extends AbstractEntitySchema<Long> {

    @Column(nullable = false, length = 20)
    protected String role;

    @Column(nullable = false, length = 60)
    private String firstName;

    @NotBlank
    @Column(nullable = false, length = 60)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private Gender gender;

    public UserSchema() {
    }

    public UserSchema(String firstName, String lastName, String email, String passwordHash, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Gender getGender() {
        return gender;
    }
}

