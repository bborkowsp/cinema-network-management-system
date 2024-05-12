package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.response.ScreeningTimeResponse;
import org.example.cinemabackend.cinema.core.domain.ScreeningTime;

import java.util.List;

public interface ScreeningTimeMapper {

    ScreeningTimeResponse mapScreeningTimeToScreeningTimeResponse(ScreeningTime screeningTime);

    List<ScreeningTimeResponse> mapScreeningTimesToScreeningTimeResponses(List<ScreeningTime> screeningTimes);
}
