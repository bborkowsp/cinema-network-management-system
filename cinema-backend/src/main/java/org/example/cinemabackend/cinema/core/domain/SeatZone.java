package org.example.cinemabackend.cinema.core.domain;

import java.math.BigDecimal;

public enum SeatZone {
    STANDARD(27.90),
    VIP(35.90),
    PROMO(19.90),
    WHEELCHAIR(19.90),
    CORRIDOR(null);

    private final Double price;

    SeatZone(Double price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return BigDecimal.valueOf(price);
    }
}

