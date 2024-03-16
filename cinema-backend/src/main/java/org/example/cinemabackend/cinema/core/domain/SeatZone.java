package org.example.cinemabackend.cinema.core.domain;

public enum SeatZone {
    STANDARD(27.90),
    VIP(35.90),
    PROMO(19.90),
    WHEELCHAIR(19.90);

    private final Double price;

    SeatZone(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }
}

