package org.example.cinemabackend.movie.core.domain;

public enum AgeRestriction {
    PLUS4("4+"),
    PLUS7("7+"),
    PLUS12("12+"),
    PLUS15("15+"),
    PLUS18("18+");

    private final String ageRestriction;

    AgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

}