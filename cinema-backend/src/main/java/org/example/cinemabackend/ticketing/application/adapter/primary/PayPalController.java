package org.example.cinemabackend.ticketing.application.adapter.primary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.ticketing.application.dto.request.BuyTicketRequest;
import org.example.cinemabackend.ticketing.core.domain.PayPalCompletedOrder;
import org.example.cinemabackend.ticketing.core.domain.PayPalPaymentOrder;
import org.example.cinemabackend.ticketing.core.port.primary.PayPalUseCases;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/paypal")
@RequiredArgsConstructor
public class PayPalController {
    private final PayPalUseCases payPalUseCases;

    @PostMapping("/init-payment")
    ResponseEntity<PayPalPaymentOrder> createPayment(@RequestBody BuyTicketRequest sum) {
        final var payPalPaymentOrder = payPalUseCases.createPayment(sum);
        return ResponseEntity.ok(payPalPaymentOrder);
    }

    @PostMapping("/capture")
    ResponseEntity<PayPalCompletedOrder> completePayment(@RequestParam("token") String token) {
        final var payPalCompletedOrder = payPalUseCases.completePayment(token);
        return ResponseEntity.ok(payPalCompletedOrder);
    }
}
