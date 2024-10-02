package org.example.cinemabackend.ticketing.core.port.primary;

import org.example.cinemabackend.ticketing.application.dto.request.BuyTicketRequest;
import org.example.cinemabackend.ticketing.core.domain.PayPalCompletedOrder;
import org.example.cinemabackend.ticketing.core.domain.PayPalPaymentOrder;

public interface PayPalUseCases {

    PayPalPaymentOrder createPayment(BuyTicketRequest buyTicketRequest);

    PayPalCompletedOrder completePayment(String token);
}
