package org.example.cinemabackend.cinema.core.domain;

public class Address {
    private String streetAndBuildingNumber;
    private String city;
    private String postalCode;
    private String country;

    public Address(String streetAndBuildingNumber, String city, String postalCode, String country) {
        this.streetAndBuildingNumber = streetAndBuildingNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }


    public String getStreetAndBuildingNumber() {
        return this.streetAndBuildingNumber;
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

    public void setStreetAndBuildingNumber(String streetAndBuildingNumber) {
        this.streetAndBuildingNumber = streetAndBuildingNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
