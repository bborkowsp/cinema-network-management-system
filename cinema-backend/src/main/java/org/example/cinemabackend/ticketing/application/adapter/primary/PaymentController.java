package org.example.cinemabackend.ticketing.application.adapter.primary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.ticketing.application.dto.request.BuyTicketRequest;
import org.example.cinemabackend.ticketing.application.dto.request.FinalizePaymentRequest;
import org.example.cinemabackend.ticketing.core.domain.PaymentCompletedOrder;
import org.example.cinemabackend.ticketing.core.domain.PaymentOrder;
import org.example.cinemabackend.ticketing.core.port.primary.PaymentUseCases;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentUseCases paymentUseCases;

    @PostMapping("/init-payment")
    ResponseEntity<PaymentOrder> initPayment(@RequestBody BuyTicketRequest buyTicketRequest) {
        final var paymentOrder = paymentUseCases.initPayment(buyTicketRequest);
        return ResponseEntity.ok(paymentOrder);
    }

    @PostMapping("/finalize-payment")
    ResponseEntity<PaymentCompletedOrder> finalizePayment(@RequestBody FinalizePaymentRequest finalizePaymentRequest) {
        final var payPalCompletedOrder = paymentUseCases.finalizePayment(finalizePaymentRequest);
        return ResponseEntity.ok(payPalCompletedOrder);
    }
}
