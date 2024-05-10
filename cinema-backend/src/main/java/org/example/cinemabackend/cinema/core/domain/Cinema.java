package org.example.cinemabackend.cinema.core.domain;

import org.example.cinemabackend.movie.core.domain.Image;
import org.example.cinemabackend.user.core.domain.User;

import java.util.Set;

public class Cinema {
    private Long id;
    private String name;
    private String description;
    private Address address;
    private Image image;
    private Set<Screening> repertory;
    private Set<ScreeningRoom> screeningRooms;
    private Set<ContactDetails> contactDetails;
    private User cinemaManager;

    public Cinema(String name, String description, Address address, Image image, Set<Screening> repertory, Set<ScreeningRoom> screeningRooms, Set<ContactDetails> contactDetails, User cinemaManager) {
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

    public Cinema(Long id, String name, String description, Address address, Image image, Set<Screening> repertory, Set<ScreeningRoom> screeningRooms, Set<ContactDetails> contactDetails, User cinemaManager) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.repertory = repertory;
        this.image = image;
        this.screeningRooms = screeningRooms;
        this.contactDetails = contactDetails;
        this.cinemaManager = cinemaManager;
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

    public User getCinemaManager() {
        return cinemaManager;
    }

    public void setCinemaManager(User cinemaManager) {
        this.cinemaManager = cinemaManager;
    }

    public Long getId() {
        return this.id;
    }
}
