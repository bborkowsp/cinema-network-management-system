package org.example.cinemabackend.cinema.core.domain;

public class ContactType {
    private Long id;
    private String phoneNumber;
    private String email;

    public ContactType(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public ContactType(Long id, String phoneNumber, String email) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }
}
