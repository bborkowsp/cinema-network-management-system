package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.CreateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.response.CinemaResponse;
import org.example.cinemabackend.cinema.application.dto.response.CinemaTableResponse;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.SeatType;
import org.example.cinemabackend.cinema.core.port.primary.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class CinemaMapperService implements CinemaMapper {

    private final AddressMapper addressMapper;
    private final ImageMapper imageMapper;
    private final ScreeningRoomMapper screeningRoomMapper;
    private final ContactDetailsMapper contactDetailsMapper;
    private final CinemaManagerMapper cinemaManagerMapper;

    @Override
    public CinemaResponse mapCinemaToCinemaResponse(Cinema cinema) {
        return CinemaResponse.builder()
                .name(cinema.getName())
                .description(cinema.getDescription())
                .address(addressMapper.mapAddressToAddressResponse(cinema.getAddress()))
                .image(imageMapper.mapImageToImageResponse(cinema.getImage()))
                .contactDetails(contactDetailsMapper.mapContactDetailsToContactDetailsResponse(cinema.getContactDetails()))
                .cinemaManager(cinemaManagerMapper.mapCinemaManagerToCinemaManagerResponse(cinema.getCinemaManager()))
                .build();
    }

    @Override
    public Cinema mapCreateCinemaRequestToCinema(CreateCinemaRequest createCinemaRequest) {
        return new Cinema(
                createCinemaRequest.name(),
                createCinemaRequest.description(),
                addressMapper.mapCreateAddressRequestToAddress(createCinemaRequest.address()),
                imageMapper.mapCreateImageRequestToImage(createCinemaRequest.image()),
                screeningRoomMapper.mapCreateScreeningRoomToScreeningRoom(createCinemaRequest.screeningRooms()),
                contactDetailsMapper.mapCreateContactDetailsToContactDetails(createCinemaRequest.contactDetails())
        );
    }

    @Override
    public CinemaTableResponse mapCinemaToCinemaTableRow(Cinema cinema) {
        final var numberOfAvailableSeats = getNumberOfAvailableSeats(cinema);
        final var numberOfUnavailableSeats = getNumberOfUnavailableSeats(cinema);
        return CinemaTableResponse.builder()
                .name(cinema.getName())
                .cinemaManager(cinema.getCinemaManager().getFirstName() + " " + cinema.getCinemaManager().getLastName())
                .numberOfScreeningRooms(String.valueOf(cinema.getScreeningRooms().size()))
                .numberOfAvailableSeats(numberOfAvailableSeats)
                .numberOfUnavailableSeats(numberOfUnavailableSeats)
                .build();
    }

    private String getNumberOfUnavailableSeats(Cinema cinema) {
        return String.valueOf(cinema.getScreeningRooms().stream()
                .flatMap(room -> room.getSeats().stream())
                .filter(seat -> seat.getSeatType() == SeatType.UNAVAILABLE)
                .count());
    }

    private String getNumberOfAvailableSeats(Cinema cinema) {
        return String.valueOf(cinema.getScreeningRooms().stream()
                .flatMap(room -> room.getSeats().stream())
                .filter(seat -> seat.getSeatType() == SeatType.AVAILABLE || seat.getSeatType() == SeatType.RESERVED || seat.getSeatType() == SeatType.OCCUPIED)
                .count());
    }
}
