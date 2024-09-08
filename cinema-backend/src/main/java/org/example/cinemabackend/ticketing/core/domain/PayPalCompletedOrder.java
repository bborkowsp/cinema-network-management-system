package org.example.cinemabackend.ticketing.core.domain;

public class PayPalCompletedOrder {
    private PayPalPaymentStatus status;
    private String token;

    public PayPalCompletedOrder(PayPalPaymentStatus status, String token) {
        this.status = status;
        this.token = token;
    }

    public PayPalCompletedOrder(PayPalPaymentStatus status) {
        this.status = status;
    }

    public PayPalPaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PayPalPaymentStatus status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
