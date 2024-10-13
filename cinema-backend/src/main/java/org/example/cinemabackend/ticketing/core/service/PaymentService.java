package org.example.cinemabackend.ticketing.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.ticketing.application.dto.request.BuyTicketRequest;
import org.example.cinemabackend.ticketing.application.dto.request.FinalizePaymentRequest;
import org.example.cinemabackend.ticketing.core.domain.PaymentCompletedOrder;
import org.example.cinemabackend.ticketing.core.domain.PaymentOrder;
import org.example.cinemabackend.ticketing.core.domain.PaymentStrategy;
import org.example.cinemabackend.ticketing.core.domain.PaymentStrategyFactory;
import org.example.cinemabackend.ticketing.core.port.primary.PaymentUseCases;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentUseCases {
    private final PaymentStrategyFactory paymentStrategyFactory;

    @Override
    public PaymentOrder initPayment(BuyTicketRequest buyTicketRequest) {
        PaymentStrategy paymentStrategy = paymentStrategyFactory.getPaymentStrategy(buyTicketRequest.paymentMethod());
        return paymentStrategy.initPayment(buyTicketRequest);
    }

    @Override
    public PaymentCompletedOrder finalizePayment(FinalizePaymentRequest finalizePaymentRequest) {
        PaymentStrategy paymentStrategy = paymentStrategyFactory.getPaymentStrategy(finalizePaymentRequest.paymentMethod());
        return paymentStrategy.finalizePayment(finalizePaymentRequest);
    }
}
