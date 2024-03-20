package org.example.cinemabackend.cinema.core.domain;

public class ContactType {
    private String phoneNumber;
    private String email;

    public ContactType(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
