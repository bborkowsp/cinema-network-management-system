package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.request.ScreeningRequest;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningResponse;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningMapper;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningRoomMapper;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRoomRepository;
import org.example.cinemabackend.movie.core.port.primary.MovieMapper;
import org.example.cinemabackend.movie.core.port.secondary.MovieRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ScreeningMapperService implements ScreeningMapper {
    private final MovieMapper movieMapper;
    private final ScreeningRoomMapper screeningRoomMapper;
    private final MovieRepository movieRepository;
    private final ScreeningRoomRepository screeningRoomRepository;

    @Override
    public ScreeningResponse mapScreeningToScreeningResponse(Screening screening, ScreeningRoom screeningRoom) {
        return ScreeningResponse.builder()
                .id(screening.getId())
                .movie(movieMapper.mapMovieToMovieResponse(screening.getMovie()))
                .startTime(screening.getStartTime())
                .endTime(screening.getEndTime())
                .screeningRoom(screeningRoomMapper.mapScreeningRoomToScreeningRoomResponse(screeningRoom))
                .build();
    }

    @Override
    public Screening mapScreeningRequestToScreening(ScreeningRequest screening) {
        return new Screening(
                movieRepository.findByTitle(screening.movieTitle()).orElseThrow(),
                screening.startTime(),
                screening.endTime()
        );
    }

    private ScreeningRoom findScreeningRoom(String screeningRoomName, Cinema cinema) {
        return cinema.getScreeningRooms().stream()
                .filter(screeningRoom -> screeningRoom.getName().equals(screeningRoomName))
                .findFirst()
                .orElseThrow();
    }
}
