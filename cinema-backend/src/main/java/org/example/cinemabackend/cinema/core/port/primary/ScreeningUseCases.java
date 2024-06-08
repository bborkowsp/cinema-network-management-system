package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.ScreeningRequest;
import org.example.cinemabackend.cinema.application.dto.request.update.UpdateScreeningRequest;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningResponse;

import java.util.List;

public interface ScreeningUseCases {
    List<ScreeningResponse> getScreenings();

    List<ScreeningResponse> getScreenings(String email);

    ScreeningResponse getScreening(Long id);

    void createScreening(ScreeningRequest screening);

    void updateScreening(Long id, UpdateScreeningRequest screening);

    void deleteScreening(Long id);
}
