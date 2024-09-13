package org.example.cinemabackend.ticketing.core.service;

import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.auth.core.port.primary.EmailUseCases;
import org.example.cinemabackend.cinema.core.domain.SeatStatus;
import org.example.cinemabackend.cinema.core.port.primary.SeatMapper;
import org.example.cinemabackend.ticketing.application.dto.request.BuyTicketRequest;
import org.example.cinemabackend.ticketing.core.domain.PayPalCompletedOrder;
import org.example.cinemabackend.ticketing.core.domain.PayPalPaymentOrder;
import org.example.cinemabackend.ticketing.core.domain.PayPalPaymentStatus;
import org.example.cinemabackend.ticketing.core.domain.Ticket;
import org.example.cinemabackend.ticketing.core.port.primary.PayPalUseCases;
import org.example.cinemabackend.ticketing.core.port.primary.TicketUseCases;
import org.example.cinemabackend.ticketing.core.port.secondary.TicketRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
class PayPalService implements PayPalUseCases {
    private final static String FRONTEND_BASE_URL = "http://localhost:4200";
    private final static String CANCEL_URL = FRONTEND_BASE_URL + "/cancel-paypal-payment";
    private final static String RETURN_URL = FRONTEND_BASE_URL + "/capture-paypal-payment";
    private final PayPalHttpClient payPalHttpClient;
    private final TicketUseCases ticketUseCases;
    private final TicketRepository ticketRepository;
    private final EmailUseCases emailUseCases;
    private final SeatMapper seatMapper;

    @Override
    public PayPalCompletedOrder completePayment(String token) {
        OrdersCaptureRequest ordersCaptureRequest = new OrdersCaptureRequest(token);
        try {
            HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersCaptureRequest);
            if (httpResponse.result().status() != null) {
                final var ticket = findTicket(token);
                emailUseCases.sendTicketToUser(ticket);
                ticketUseCases.changeSeatStatus(ticket.getBookedSeats(), SeatStatus.SOLD);
                return new PayPalCompletedOrder(PayPalPaymentStatus.SUCCESS, token);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to capture payment");
        }
        return new PayPalCompletedOrder(PayPalPaymentStatus.FAILED);
    }

    private Ticket findTicket(String token) {
        ticketRepository.findByOrderId(token).orElseThrow();
    }

    @Override
    public PayPalPaymentOrder createPayment(BuyTicketRequest buyTicketRequest) {
        ticketUseCases.validateSeatsAreAvailable(buyTicketRequest.selectedSeats());
        final var bookedSeats = seatMapper.mapSeatResponsesToSeat(buyTicketRequest.selectedSeats());
        ticketUseCases.changeSeatStatus(bookedSeats, SeatStatus.RESERVED);
        OrderRequest orderRequest = createOrderRequest(buyTicketRequest);
        OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);

        try {
            HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
            Order order = orderHttpResponse.result();
            String redirectUrl = getRedirectUrl(order);
            ticketUseCases.generateTickets(buyTicketRequest, order.id());
            return new PayPalPaymentOrder(PayPalPaymentStatus.SUCCESS, order.id(), redirectUrl);
        } catch (IOException e) {
            ticketUseCases.changeSeatStatus(bookedSeats, SeatStatus.AVAILABLE);
            return new PayPalPaymentOrder(PayPalPaymentStatus.FAILED);
        }
    }

    private OrderRequest createOrderRequest(BuyTicketRequest buyTicketRequest) {
        final BigDecimal fee = ticketUseCases.getOrderFee(buyTicketRequest.selectedSeats());

        AmountWithBreakdown amountBreakdown = new AmountWithBreakdown()
                .currencyCode("PLN")
//                .value(fee.toString())
                .value("0.01");

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

    private String getRedirectUrl(Order order) {
        return order.links().stream()
                .filter(link -> "approve".equals(link.rel()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Approval link not found"))
                .href();
    }
}
