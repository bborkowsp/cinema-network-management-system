package org.example.cinemabackend.ticketing.core.port.primary;

import org.example.cinemabackend.ticketing.application.dto.request.BuyTicketRequest;
import org.example.cinemabackend.ticketing.core.domain.PayPalCompletedOrder;
import org.example.cinemabackend.ticketing.core.domain.PayPalPaymentOrder;

public interface PayPalUseCases {
    PayPalCompletedOrder completePayment(String token);

    PayPalPaymentOrder createPayment(BuyTicketRequest fee);
}
