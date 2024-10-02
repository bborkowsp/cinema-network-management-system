package org.example.cinemabackend.ticketing.core.port.primary;

import org.example.cinemabackend.ticketing.application.dto.request.BuyTicketRequest;
import org.example.cinemabackend.ticketing.application.dto.request.FinalizePaymentRequest;
import org.example.cinemabackend.ticketing.core.domain.PaymentCompletedOrder;
import org.example.cinemabackend.ticketing.core.domain.PaymentOrder;

public interface PaymentUseCases {

    PaymentOrder initPayment(BuyTicketRequest buyTicketRequest);

    PaymentCompletedOrder finalizePayment(FinalizePaymentRequest finalizePaymentRequest);
}
