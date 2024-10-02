package org.example.cinemabackend.ticketing.core.domain;

public class PaymentOrder {
    private PaymentStatus status;
    private String orderId;
    private String redirectUrl;

    public PaymentOrder(PaymentStatus status, String orderId, String redirectUrl) {
        this.status = status;
        this.orderId = orderId;
        this.redirectUrl = redirectUrl;
    }

    public PaymentOrder(PaymentStatus status) {
        this.status = status;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
