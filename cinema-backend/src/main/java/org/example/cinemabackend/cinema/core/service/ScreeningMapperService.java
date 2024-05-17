package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningResponse;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningMapper;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningRoomMapper;
import org.example.cinemabackend.movie.core.port.primary.MovieMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ScreeningMapperService implements ScreeningMapper {

    private final MovieMapper movieMapper;
    private final ScreeningRoomMapper screeningRoomMapper;

    @Override
    public ScreeningResponse mapScreeningToScreeningResponse(Screening screening) {
        return ScreeningResponse.builder()
                .movie(movieMapper.mapMovieToMovieResponse(screening.getMovie()))
                .startTime(screening.getStartTime())
                .endTime(screening.getEndTime())
                .screeningRoom(screeningRoomMapper.mapScreeningRoomToScreeningRoomResponse(screening.getScreeningRoom()))
                .build();
    }
}
