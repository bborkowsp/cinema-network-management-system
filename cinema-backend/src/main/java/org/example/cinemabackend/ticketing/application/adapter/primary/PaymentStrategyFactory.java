package org.example.cinemabackend.ticketing.application.adapter.primary;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.ticketing.core.domain.PaymentMethod;
import org.example.cinemabackend.ticketing.core.domain.PaymentStrategy;
import org.example.cinemabackend.ticketing.infrastructure.adapter.secondary.PayPalPaymentStrategy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyFactory {
    private final PayPalPaymentStrategy payPalPaymentStrategy;

    public PaymentStrategy getPaymentStrategy(@NotBlank String paymentMethod) {
        if (paymentMethod.equals(PaymentMethod.PAYPAL.toString())) {
            return payPalPaymentStrategy;
        } else {
            throw new IllegalArgumentException("Payment method not supported");
        }
    }
}
