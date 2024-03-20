package org.example.cinemabackend.cinema.core.domain;

public class Address {
    private String street;
    private String buildingNumber;
    private String city;
    private String postalCode;
    private String country;

    public Address(String street, String buildingNumber, String city, String postalCode, String country) {
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getStreet() {
        return this.street;
    }

    public String getBuildingNumber() {
        return this.buildingNumber;
    }

    public String getCity() {
        return this.city;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public String getCountry() {
        return this.country;
    }

}
