package org.example.cinemabackend.user.core.domain;

public class CinemaManager extends User {
    public CinemaManager() {
        this.role = "ROLE_CINEMA_MANAGER";
    }

    public CinemaManager(String firstName, String lastName, String email, String passwordHash, Gender gender) {
        super(firstName, lastName, email, passwordHash, gender);
        this.role = "ROLE_CINEMA_MANAGER";
    }
}
