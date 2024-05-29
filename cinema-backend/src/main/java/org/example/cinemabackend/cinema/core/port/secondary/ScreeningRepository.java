package org.example.cinemabackend.cinema.core.port.secondary;

import org.example.cinemabackend.cinema.core.domain.Screening;

import java.util.List;
import java.util.Optional;

public interface ScreeningRepository {

    Optional<Screening> findById(Long id);

    List<Screening> findAll();

    void deleteById(Long id);

    void save(Screening screeningToUpdate);
}
