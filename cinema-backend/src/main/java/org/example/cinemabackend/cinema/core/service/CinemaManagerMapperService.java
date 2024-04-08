package org.example.cinemabackend.cinema.core.service;

import org.example.cinemabackend.cinema.application.dto.response.CinemaManagerResponse;
import org.example.cinemabackend.cinema.core.port.primary.CinemaManagerMapper;
import org.example.cinemabackend.user.core.domain.CinemaManager;
import org.springframework.stereotype.Service;

@Service
class CinemaManagerMapperService implements CinemaManagerMapper {
    
    @Override
    public CinemaManagerResponse mapCinemaManagerToCinemaManagerResponse(CinemaManager cinemaManager) {
        return CinemaManagerResponse.builder()
                .firstName(cinemaManager.getFirstName())
                .lastName(cinemaManager.getLastName())
                .email(cinemaManager.getEmail())
                .build();
    }
}
