package org.example.cinemabackend.cinema.core.domain;

import org.example.cinemabackend.movie.core.domain.Image;
import org.example.cinemabackend.user.core.domain.CinemaManager;

import java.util.Set;

public class Cinema extends AbstractEntity<Long> {
    private String name;
    private String description;
    private Address address;
    private Image image;
    private Set<Screening> repertory;
    private Set<ScreeningRoom> screeningRooms;
    private Set<ContactDetails> contactDetails;
    private CinemaManager cinemaManager;

    public Cinema(String name, String description, Address address, Image image, Set<Screening> repertory, Set<ScreeningRoom> screeningRooms, Set<ContactDetails> contactDetails, CinemaManager cinemaManager) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.repertory = repertory;
        this.image = image;
        this.screeningRooms = screeningRooms;
        this.contactDetails = contactDetails;
        this.cinemaManager = cinemaManager;
    }

    public Cinema(String name, String description, Address address, Image image, Set<ScreeningRoom> screeningRooms, Set<ContactDetails> contactDetails) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.image = image;
        this.screeningRooms = screeningRooms;
        this.contactDetails = contactDetails;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Address getAddress() {
        return address;
    }

    public Image getImage() {
        return image;
    }

    public Set<Screening> getRepertory() {
        return repertory;
    }

    public Set<ScreeningRoom> getScreeningRooms() {
        return screeningRooms;
    }

    public Set<ContactDetails> getContactDetails() {
        return contactDetails;
    }

    public CinemaManager getCinemaManager() {
        return cinemaManager;
    }
}
