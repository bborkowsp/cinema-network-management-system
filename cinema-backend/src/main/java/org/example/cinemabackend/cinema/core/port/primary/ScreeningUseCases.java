package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.ScreeningRequest;
import org.example.cinemabackend.cinema.application.dto.request.UpdateScreeningRequest;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningResponse;

import java.util.List;

public interface ScreeningUseCases {
    List<ScreeningResponse> getScreenings();

    List<ScreeningResponse> getScreenings(String email);

    void deleteScreening(Long id);

    void updateScreening(Long id, UpdateScreeningRequest screening);

    ScreeningResponse getScreening(Long id);

    void createScreening(ScreeningRequest screening);
}
