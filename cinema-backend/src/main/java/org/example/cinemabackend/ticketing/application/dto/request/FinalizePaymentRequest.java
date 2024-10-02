package org.example.cinemabackend.ticketing.application.dto.request;

import lombok.Builder;

@Builder
public record FinalizePaymentRequest(
        String token,
        String paymentMethod
) {
}
