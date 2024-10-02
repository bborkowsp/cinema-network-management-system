package org.example.cinemabackend.ticketing.core.domain;

public enum PaymentMethod {
    PAYPAL {
        @Override
        public String toString() {
            return "PayPal";
        }
    },
}
