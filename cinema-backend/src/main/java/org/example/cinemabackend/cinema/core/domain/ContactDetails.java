package org.example.cinemabackend.cinema.core.domain;

public class ContactDetails {
    private Long id;
    private String department;
    private ContactType contactType;

    public ContactDetails(String department, ContactType contactType) {
        this.department = department;
        this.contactType = contactType;
    }

    public ContactDetails(Long id, String department, ContactType contactType) {
        this.id = id;
        this.department = department;
        this.contactType = contactType;
    }

    public String getDepartment() {
        return department;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public Long getId() {
        return id;
    }
}
