package org.example.cinemabackend.ticketing.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.auth.core.port.primary.AuthUseCases;
import org.example.cinemabackend.cinema.application.dto.response.SeatResponse;
import org.example.cinemabackend.cinema.core.domain.*;
import org.example.cinemabackend.cinema.core.port.primary.SeatMapper;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRoomRepository;
import org.example.cinemabackend.cinema.core.port.secondary.SeatRepository;
import org.example.cinemabackend.ticketing.application.dto.request.BuyTicketRequest;
import org.example.cinemabackend.ticketing.application.dto.response.TicketResponse;
import org.example.cinemabackend.ticketing.core.domain.Ticket;
import org.example.cinemabackend.ticketing.core.port.primary.QrCodeUseCases;
import org.example.cinemabackend.ticketing.core.port.primary.TicketMapper;
import org.example.cinemabackend.ticketing.core.port.primary.TicketUseCases;
import org.example.cinemabackend.ticketing.core.port.secondary.TicketRepository;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
class TicketService implements TicketUseCases {
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final QrCodeUseCases qrCodeService;
    private final SeatMapper seatMapper;
    private final ScreeningRepository screeningRepository;
    private final ScreeningRoomRepository screeningRoomRepository;
    private final CinemaRepository cinemaRepository;
    private final TicketRepository ticketRepository;
    private final AuthUseCases authUseCases;
    private final TicketMapper ticketMapper;

    @Override
    public List<TicketResponse> getTickets() {
        final var email = authUseCases.getCurrentUserEmail();
        return ticketRepository.findAllByEmail(email).stream().map(ticketMapper::mapTicketToTicketResponse).toList();
    }

    @Override
    public void generateTickets(BuyTicketRequest buyTicketRequest, String orderId) {
        BufferedImage qrImage = qrCodeService.generateQrCodeImage(createQrCodeText(buyTicketRequest, orderId));
        User user = userRepository.findByEmail(buyTicketRequest.email()).orElse(null);
        handleTicket(buyTicketRequest, user, qrImage, orderId);
    }

    private StringBuilder createQrCodeText(BuyTicketRequest buyTicketRequest, String orderId) {
        StringBuilder qrCodeText = new StringBuilder();
        qrCodeText.append("Order ID: ").append(orderId)
                .append(".Movie ID: ").append(buyTicketRequest.movieId());
        return qrCodeText;
    }

    private void handleTicket(BuyTicketRequest buyTicketRequest, User user, BufferedImage qrImage, String orderId) {
        var seats = seatMapper.mapSeatResponsesToSeat(buyTicketRequest.selectedSeats());
        var screening = findScreening(buyTicketRequest.movieId());
        var screeningRoom = findScreeningRoom(screening);
        var cinema = findCinema(screeningRoom);
        var qrImageBytes = qrCodeService.convertBufferedImageToByteArray(qrImage);

        Ticket ticket = new Ticket(
                buyTicketRequest.email(),
                orderId,
                buyTicketRequest.firstName(),
                buyTicketRequest.lastName(),
                qrImageBytes,
                seats,
                screening,
                screeningRoom,
                cinema
        );

        if (user != null) {
            ticket.setUser(user);
        }

        ticketRepository.save(ticket);
    }

    private Screening findScreening(Long movieId) {
        return screeningRepository.findById(movieId).orElseThrow();
    }

    private ScreeningRoom findScreeningRoom(Screening screening) {
        return screeningRoomRepository.findByRepertoryContains(screening).orElseThrow();
    }

    private Cinema findCinema(ScreeningRoom screeningRoom) {
        return cinemaRepository.findByScreeningRoom(screeningRoom).orElseThrow();
    }

    @Override
    public void changeSeatStatus(List<Seat> seatResponses, SeatStatus seatStatus) {
        seatResponses.forEach(seatResponse ->
                seatRepository.findById(seatResponse.getId()).ifPresent(seat -> {
                    seat.setSeatStatus(seatStatus);
                    seatRepository.save(seat);
                })
        );
    }

    @Override
    public void validateSeatsAreAvailable(List<SeatResponse> seatResponses) {
        for (SeatResponse seatResponse : seatResponses) {
            Seat seat = seatRepository.findById(seatResponse.id())
                    .orElseThrow(() -> new IllegalStateException("Seat not found"));

            if (seat.getSeatStatus() != SeatStatus.AVAILABLE) {
                throw new IllegalStateException("Selected seats have been sold out!");
            }
        }
    }

    @Override
    public BigDecimal getOrderFee(List<SeatResponse> seatResponses) {
        return seatResponses.stream()
                .map(seatResponse -> seatResponse.seatZone().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}