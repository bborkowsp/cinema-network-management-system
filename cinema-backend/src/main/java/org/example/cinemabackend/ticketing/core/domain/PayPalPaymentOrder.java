package org.example.cinemabackend.ticketing.core.domain;

public class PayPalPaymentOrder {
    private PayPalPaymentStatus status;
    private String orderId;
    private String redirectUrl;

    public PayPalPaymentOrder(PayPalPaymentStatus status, String orderId, String redirectUrl) {
        this.status = status;
        this.orderId = orderId;
        this.redirectUrl = redirectUrl;
    }

    public PayPalPaymentOrder(PayPalPaymentStatus status) {
        this.status = status;
    }

    public PayPalPaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PayPalPaymentStatus status) {
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
