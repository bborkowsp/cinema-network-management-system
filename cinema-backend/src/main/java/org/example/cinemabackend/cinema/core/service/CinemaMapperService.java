package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.CreateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.response.CinemaResponse;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.port.primary.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class CinemaMapperService implements CinemaMapper {

    private final AddressMapper addressMapper;
    private final ImageMapper imageMapper;
    private final ScreeningRoomMapper screeningRoomMapper;
    private final ContactDetailsMapper contactDetailsMapper;

    @Override
    public CinemaResponse mapCinemaToCinemaResponse(Cinema cinema) {
        return CinemaResponse.builder()
                .name(cinema.getName())
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
}
