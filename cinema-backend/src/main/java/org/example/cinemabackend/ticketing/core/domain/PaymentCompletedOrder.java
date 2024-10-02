package org.example.cinemabackend.ticketing.core.domain;

public class PaymentCompletedOrder {
    private PaymentStatus status;
    private String token;

    public PaymentCompletedOrder(PaymentStatus status, String token) {
        this.status = status;
        this.token = token;
    }

    public PaymentCompletedOrder(PaymentStatus status) {
        this.status = status;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
