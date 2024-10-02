package org.example.cinemabackend.ticketing.core.domain;

import org.example.cinemabackend.ticketing.application.dto.request.BuyTicketRequest;
import org.example.cinemabackend.ticketing.application.dto.request.FinalizePaymentRequest;

public interface PaymentStrategy {

    PaymentOrder initPayment(BuyTicketRequest buyTicketRequest);

    PaymentCompletedOrder finalizePayment(FinalizePaymentRequest finalizePaymentRequest);
}
