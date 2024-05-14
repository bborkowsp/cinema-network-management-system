package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.response.ScreeningResponse;

import java.util.List;

public interface ScreeningUseCases {
    List<ScreeningResponse> getScreenings();

    List<ScreeningResponse> getScreenings(String email);
}
