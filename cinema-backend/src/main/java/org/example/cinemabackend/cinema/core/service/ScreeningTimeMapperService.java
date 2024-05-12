package org.example.cinemabackend.cinema.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningTimeResponse;
import org.example.cinemabackend.cinema.core.domain.ScreeningTime;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningRoomMapper;
import org.example.cinemabackend.cinema.core.port.primary.ScreeningTimeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ScreeningTimeMapperService implements ScreeningTimeMapper {

    private final ScreeningRoomMapper screeningRoomMapper;

    @Override
    public ScreeningTimeResponse mapScreeningTimeToScreeningTimeResponse(ScreeningTime screeningTime) {
        return ScreeningTimeResponse.builder()
                .screeningRoom(screeningRoomMapper.mapScreeningRoomToScreeningRoomResponse(screeningTime.getScreeningRoom()))
                .time(screeningTime.getTime())
                .build();
    }

    @Override
    public List<ScreeningTimeResponse> mapScreeningTimesToScreeningTimeResponses(List<ScreeningTime> screeningTimes) {
        return screeningTimes.stream().map(this::mapScreeningTimeToScreeningTimeResponse).toList();
    }
}
