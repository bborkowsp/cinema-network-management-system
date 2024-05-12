package org.example.cinemabackend.cinema.core.port.secondary;

import org.example.cinemabackend.cinema.core.domain.Screening;

import java.util.List;

public interface ScreeningRepository {

    List<Screening> findAll();

}
