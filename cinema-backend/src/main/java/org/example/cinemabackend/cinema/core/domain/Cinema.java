package org.example.cinemabackend.cinema.core.domain;

import org.example.cinemabackend.movie.core.domain.Image;
import org.example.cinemabackend.user.core.domain.User;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cinema {
    private Long id;
    private String name;
    private String description;
    private Address address;
    private Image image;
    private Set<ScreeningRoom> screeningRooms = new HashSet<>();
    private Set<ContactDetails> contactDetails = new HashSet<>();
    private User cinemaManager;

    public Cinema(String name, String description, Address address, Image image, Set<ScreeningRoom> screeningRooms, Set<ContactDetails> contactDetails, User cinemaManager) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.image = image;
        this.screeningRooms = screeningRooms;
        this.contactDetails = contactDetails;
        this.cinemaManager = cinemaManager;
    }

    public Cinema(Long id, String name, String description, Address address, Image image, Set<ScreeningRoom> screeningRooms, Set<ContactDetails> contactDetails, User cinemaManager) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.image = image;
        this.screeningRooms = screeningRooms;
        this.contactDetails = contactDetails;
        this.cinemaManager = cinemaManager;
    }

    public Cinema(String name, String description, Address address, Image image, User cinemaManager) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.image = image;
        this.cinemaManager = cinemaManager;
    }

    public Cinema(String name, String description, Address address, Image image) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.image = image;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }


    public Set<ScreeningRoom> getScreeningRooms() {
        return screeningRooms;
    }

    public void setScreeningRooms(Set<ScreeningRoom> screeningRooms) {
        this.screeningRooms = screeningRooms;
    }

    public Set<ContactDetails> getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(Set<ContactDetails> contactDetails) {
        this.contactDetails = contactDetails;
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

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Cinema cinema)) {
            return false;
        }

        return Objects.equals(name, cinema.name);
    }

    public void addScreeningRoom(ScreeningRoom screeningRoom) {
        this.screeningRooms.add(screeningRoom);
    }

    public void addContactDetails(ContactDetails contactDetails) {
        this.contactDetails.add(contactDetails);
    }

}
