package org.example.cinemabackend.cinema.core.domain;

import org.example.cinemabackend.movie.core.domain.Image;

import java.util.Set;

public class Cinema extends AbstractEntity<Long> {
    private String name;
    private String description;
    private Address address;
    private Set<Screening> repertory;
    private Image image;
    private Set<ScreeningRoom> screeningRooms;

    private ContactDetails contactDetails;
}
