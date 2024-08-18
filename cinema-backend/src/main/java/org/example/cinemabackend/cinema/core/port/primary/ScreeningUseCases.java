package org.example.cinemabackend.cinema.core.port.primary;

import org.example.cinemabackend.cinema.application.dto.request.create.CreateScreeningRequest;
import org.example.cinemabackend.cinema.application.dto.response.ScreeningResponse;

import java.time.LocalDate;
import java.util.List;

public interface ScreeningUseCases {
    List<ScreeningResponse> getScreenings(String email);

    List<ScreeningResponse> getRepertory(String cinema);

    List<ScreeningResponse> getRepertoryAtSpecificDate(String cinema, LocalDate date);

    ScreeningResponse getScreening(Long id);

    void createScreening(CreateScreeningRequest screening);

    void updateScreening(Long id, CreateScreeningRequest screening);

    void deleteScreening(Long id);
}
