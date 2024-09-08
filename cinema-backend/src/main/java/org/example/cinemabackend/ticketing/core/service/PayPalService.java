package org.example.cinemabackend.ticketing.core.service;

import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.ticketing.application.dto.request.BuyTicketRequest;
import org.example.cinemabackend.ticketing.core.domain.PayPalCompletedOrder;
import org.example.cinemabackend.ticketing.core.domain.PayPalPaymentOrder;
import org.example.cinemabackend.ticketing.core.domain.PayPalPaymentStatus;
import org.example.cinemabackend.ticketing.core.port.primary.PayPalUseCases;
import org.example.cinemabackend.ticketing.core.port.primary.SeatReservationUseCases;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
class PayPalService implements PayPalUseCases {
    private final static String FRONTEND_BASE_URL = "https://localhost:4200";
    private final static String CANCEL_URL = FRONTEND_BASE_URL + "/cancel";
    private final static String RETURN_URL = FRONTEND_BASE_URL + "/capture";
    private final PayPalHttpClient payPalHttpClient;
    private final SeatReservationUseCases seatReservationUseCases;

    @Override
    public PayPalCompletedOrder completePayment(String token) {
        OrdersCaptureRequest ordersCaptureRequest = new OrdersCaptureRequest(token);
        try {
            HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersCaptureRequest);
            if (httpResponse.result().status() != null) {
                return new PayPalCompletedOrder(PayPalPaymentStatus.SUCCESS, token);
            }
        } catch (IOException e) {
        }
        return new PayPalCompletedOrder(PayPalPaymentStatus.FAILED);
    }

    @Override
    public PayPalPaymentOrder createPayment(BuyTicketRequest buyTicketRequest) {
        seatReservationUseCases.validateSeatsAreAvailable(buyTicketRequest.selectedSeats());
        OrderRequest orderRequest = createOrderRequest(buyTicketRequest);
        OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);

        try {
            HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
            Order order = orderHttpResponse.result();

            String redirectUrl = order.links().stream()
                    .filter(link -> "approve".equals(link.rel()))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new)
                    .href();

            return new PayPalPaymentOrder(PayPalPaymentStatus.SUCCESS, order.id(), redirectUrl);
        } catch (IOException e) {
            return new PayPalPaymentOrder(PayPalPaymentStatus.FAILED);
        }
    }

    private OrderRequest createOrderRequest(BuyTicketRequest buyTicketRequest) {
        final BigDecimal fee = seatReservationUseCases.getOrderFee(buyTicketRequest.selectedSeats());

        AmountWithBreakdown amountBreakdown = new AmountWithBreakdown()
                .currencyCode("USD")
                .value(fee.toString());

        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .amountWithBreakdown(amountBreakdown);


        ApplicationContext applicationContext = new ApplicationContext()
                .returnUrl(RETURN_URL)
                .cancelUrl(CANCEL_URL);

        return new OrderRequest()
                .checkoutPaymentIntent("CAPTURE")
                .purchaseUnits(List.of(purchaseUnitRequest))
                .applicationContext(applicationContext);
    }
}
