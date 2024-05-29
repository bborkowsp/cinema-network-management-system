package org.example.cinemabackend.cinema.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.create.CreateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateCinemaRequest;
import org.example.cinemabackend.cinema.application.dto.response.CinemaResponse;
import org.example.cinemabackend.cinema.application.dto.response.CinemaTableResponse;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.domain.Seat;
import org.example.cinemabackend.cinema.core.domain.SeatType;
import org.example.cinemabackend.cinema.core.port.primary.*;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.primary.UserMapper;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class CinemaMapperService implements CinemaMapper {

    private final AddressMapper addressMapper;
    private final ImageMapper imageMapper;
    private final ScreeningRoomMapper screeningRoomMapper;
    private final ContactDetailsMapper contactDetailsMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    public CinemaTableResponse mapCinemaToCinemaTableRow(Cinema cinema) {
        final var numberOfAvailableSeats = getNumberOfAvailableSeats(cinema);
        final var numberOfUnavailableSeats = getNumberOfUnavailableSeats(cinema);
        return CinemaTableResponse.builder()
                .name(cinema.getName())
                .cinemaManager(cinema.getCinemaManager() != null ? cinema.getCinemaManager().getFirstName() + " " + cinema.getCinemaManager().getLastName() : "N/A")
                .numberOfScreeningRooms(cinema.getScreeningRooms().size())
                .numberOfAvailableSeats(numberOfAvailableSeats)
                .numberOfUnavailableSeats(numberOfUnavailableSeats)
                .build();
    }

    @Override
    public CinemaResponse mapCinemaToCinemaResponse(@NonNull Cinema cinema) {
        return CinemaResponse.builder()
                .name(cinema.getName())
                .description(cinema.getDescription())
                .address(addressMapper.mapAddressToAddressResponse(cinema.getAddress()))
                .image(imageMapper.mapImageToImageResponse(cinema.getImage()))
                .screeningRooms(screeningRoomMapper.mapScreeningRoomToScreeningRoomResponses(cinema.getScreeningRooms()))
                .contactDetails(contactDetailsMapper.mapContactDetailsToContactDetailsResponse(cinema.getContactDetails()))
                .cinemaManager(userMapper.mapUserToUserResponse(cinema.getCinemaManager()))
                .build();
    }

    @Override
    public Cinema mapCreateCinemaRequestToCinema(@NonNull CreateCinemaRequest createCinemaRequest) {
        final var cinemaManager = findCinemaManager(createCinemaRequest.cinemaManager().email());
        return new Cinema(
                createCinemaRequest.name(),
                createCinemaRequest.description(),
                addressMapper.mapCreateAddressRequestToAddress(createCinemaRequest.address()),
                imageMapper.mapCreateImageRequestToImage(createCinemaRequest.image()),
                screeningRoomMapper.mapCreateScreeningRoomToScreeningRoom(createCinemaRequest.screeningRooms()),
                contactDetailsMapper.mapCreateContactDetailsToContactDetails(createCinemaRequest.contactDetails()),
                cinemaManager
        );
    }

    @Override
    public void updateCinemaFromUpdateCinemaRequest(
            @NonNull UpdateCinemaRequest updateCinemaRequest,
            @NonNull Cinema cinema
    ) {
        final var cinemaManager = userRepository.findByEmail(updateCinemaRequest.cinemaManager().email()).orElseThrow();
        cinema.setName(updateCinemaRequest.name());
        cinema.setDescription(updateCinemaRequest.description());
        cinema.setAddress(addressMapper.mapUpdateAddressRequestToAddress(updateCinemaRequest.address()));
        cinema.setImage(
                imageMapper.mapUpdateImageRequestToImage(
                        updateCinemaRequest.image(),
                        cinema.getImage()
                ));
        cinema.setScreeningRooms(
                screeningRoomMapper.mapUpdateScreeningRoomToScreeningRoom(
                        updateCinemaRequest.screeningRooms(),
                        cinema.getScreeningRooms()
                ));
        cinema.setContactDetails(
                contactDetailsMapper.mapUpdateContactDetailsToContactDetails(
                        updateCinemaRequest.contactDetails(),
                        cinema.getContactDetails()
                ));
        cinema.setCinemaManager(cinemaManager);
    }

    private User findCinemaManager(String email) {
        if (!email.isEmpty()) {
            return userRepository.findByEmail(email).orElseThrow();
        } else {
            return null;
        }
    }

    private int getNumberOfUnavailableSeats(Cinema cinema) {
        Set<Seat[][]> seatingPlans = cinema.getScreeningRooms().stream()
                .map(ScreeningRoom::getSeatingPlan)
                .collect(Collectors.toSet());
        int numberOfUnavailableSeats = 0;

        for (Seat[][] seatingPlan : seatingPlans) {
            for (Seat[] row : seatingPlan) {
                for (Seat seat : row) {
                    if (seat.getSeatType() == SeatType.UNAVAILABLE) {
                        numberOfUnavailableSeats++;
                    }
                }
            }
        }
        return numberOfUnavailableSeats;
    }


    private int getNumberOfAvailableSeats(Cinema cinema) {
        Set<Seat[][]> seatingPlans = cinema.getScreeningRooms().stream()
                .map(ScreeningRoom::getSeatingPlan)
                .collect(Collectors.toSet());

        int numberOfAvailableSeats = 0;
        for (Seat[][] seatingPlan : seatingPlans) {
            for (Seat[] row : seatingPlan) {
                for (Seat seat : row) {
                    if (seat.getSeatType() == SeatType.AVAILABLE) {
                        numberOfAvailableSeats++;
                    }
                }
            }
        }
        return numberOfAvailableSeats;
    }


}
