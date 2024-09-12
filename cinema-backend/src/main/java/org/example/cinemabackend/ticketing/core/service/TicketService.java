package org.example.cinemabackend.ticketing.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.auth.core.port.primary.EmailUseCases;
import org.example.cinemabackend.cinema.application.dto.response.SeatResponse;
import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.cinema.core.domain.SeatStatus;
import org.example.cinemabackend.cinema.core.port.primary.SeatMapper;
import org.example.cinemabackend.cinema.core.port.secondary.CinemaRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRepository;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRoomRepository;
import org.example.cinemabackend.cinema.core.port.secondary.SeatRepository;
import org.example.cinemabackend.ticketing.application.dto.request.BuyTicketRequest;
import org.example.cinemabackend.ticketing.core.domain.Ticket;
import org.example.cinemabackend.ticketing.core.port.primary.QrCodeUseCases;
import org.example.cinemabackend.ticketing.core.port.primary.TicketUseCases;
import org.example.cinemabackend.ticketing.core.port.secondary.TicketRepository;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class TicketService implements TicketUseCases {
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final EmailUseCases emailUseCases;
    private final QrCodeUseCases qrCodeService;
    private final SeatMapper seatMapper;
    private final ScreeningRepository screeningRepository;
    private final ScreeningRoomRepository screeningRoomRepository;
    private final CinemaRepository cinemaRepository;
    private final TicketRepository ticketRepository;

    @Override
    public void generateTickets(BuyTicketRequest buyTicketRequest, String orderId) {
        final var qrImage = qrCodeService.generateQrCodeImage(qrCodeText(buyTicketRequest, orderId));
        final var user = userRepository.findByEmail(buyTicketRequest.email());
        if (user.isPresent()) {
            handleTicketIfUserExists(buyTicketRequest, user.get(), qrImage, orderId);
        } else {
            handleTicketIfUserDoesNotExist(buyTicketRequest, qrImage, orderId);
        }
    }

    private StringBuilder qrCodeText(BuyTicketRequest buyTicketRequest, String orderId) {
        StringBuilder qrCodeText = new StringBuilder();
        qrCodeText.append("Order ID: ").append(orderId)
                .append(".Movie ID: ").append(buyTicketRequest.movieId());
        return qrCodeText;
    }

    private void handleTicketIfUserExists(BuyTicketRequest buyTicketRequest, User user, BufferedImage qrImage, String orderId) {
        final var seats = seatMapper.mapSeatResponsesToSeat(buyTicketRequest.selectedSeats());
        final var screening = screeningRepository.findById(buyTicketRequest.movieId())
                .orElseThrow(() -> new IllegalStateException("Screening not found"));
        final var screeningRoom = screeningRoomRepository.findByRepertoryContains(screening)
                .orElseThrow(() -> new IllegalStateException("Screening room not found"));
        final var cinema = cinemaRepository.findByScreeningRoom(screeningRoom)
                .orElseThrow(() -> new IllegalStateException("Cinema not found"));
        final var qrImageBytes = qrCodeService.convertBufferedImageToByteArray(qrImage);
        Ticket ticket = new Ticket(buyTicketRequest.email(), orderId, buyTicketRequest.firstName(), buyTicketRequest.lastName(), qrImageBytes, seats, screening, screeningRoom, cinema);
        ticket.setUser(user);
        ticketRepository.save(ticket);
    }

    private void handleTicketIfUserDoesNotExist(BuyTicketRequest buyTicketRequest, BufferedImage qrImage, String orderId) {
        final var seats = seatMapper.mapSeatResponsesToSeat(buyTicketRequest.selectedSeats());
        final var screening = screeningRepository.findById(buyTicketRequest.movieId())
                .orElseThrow(() -> new IllegalStateException("Screening not found"));
        final var screeningRoom = screeningRoomRepository.findByRepertoryContains(screening)
                .orElseThrow(() -> new IllegalStateException("Screening room not found"));
        final var cinema = cinemaRepository.findByScreeningRoom(screeningRoom)
                .orElseThrow(() -> new IllegalStateException("Cinema not found"));
        final var qrImageBytes = qrCodeService.convertBufferedImageToByteArray(qrImage);
        Ticket ticket = new Ticket(buyTicketRequest.email(), orderId, buyTicketRequest.firstName(), buyTicketRequest.lastName(), qrImageBytes, seats, screening, screeningRoom, cinema);
        ticketRepository.save(ticket);
    }

    @Override
    public void changeSeatStatus(List<Seat> seatResponses, SeatStatus seatStatus) {
        for (Seat seatResponse : seatResponses) {
            Optional<Seat> seat = seatRepository.findById(seatResponse.getId());
            if (seat.isPresent()) {
                seat.get().setSeatStatus(seatStatus);
                seatRepository.save(seat.get());
            }
        }
    }

    @Override
    public void validateSeatsAreAvailable(List<SeatResponse> seatResponses) {
        for (SeatResponse seatResponse : seatResponses) {
            Optional<Seat> seat = seatRepository.findById(seatResponse.id());
            if (seat.isPresent() && seat.get().getSeatStatus() != SeatStatus.AVAILABLE)
                throw new IllegalStateException("Seat is not available");
        }
    }

    @Override
    public BigDecimal getOrderFee(List<SeatResponse> seatResponses) {
        BigDecimal fee = BigDecimal.ZERO;
        for (SeatResponse seatResponse : seatResponses) {
            BigDecimal price = seatResponse.seatZone().getPrice();
            fee = fee.add(price);
        }
        return fee;
    }
}
