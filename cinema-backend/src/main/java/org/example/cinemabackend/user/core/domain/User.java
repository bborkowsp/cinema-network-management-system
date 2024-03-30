package org.example.cinemabackend.user.core.domain;

public abstract class User extends AbstractEntity<Long> {
    protected String role;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private Gender gender;

    public User(String firstName, String lastName, String email, String passwordHash, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.gender = gender;
    }

    public User() {
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
