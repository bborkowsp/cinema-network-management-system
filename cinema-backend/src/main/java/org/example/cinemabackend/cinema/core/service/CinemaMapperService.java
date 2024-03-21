package org.example.cinemabackend.cinema.core.service;

import org.example.cinemabackend.cinema.application.dto.resource.CinemaResource;
import org.example.cinemabackend.cinema.core.domain.Cinema;
import org.example.cinemabackend.cinema.core.port.primary.CinemaMapper;
import org.springframework.stereotype.Service;

@Service
class CinemaMapperService implements CinemaMapper {
    @Override
    public CinemaResource mapCinemaToCinemaResource(Cinema cinema) {
        return CinemaResource.builder()
                .name(cinema.getName())
                .build();
    }
}
