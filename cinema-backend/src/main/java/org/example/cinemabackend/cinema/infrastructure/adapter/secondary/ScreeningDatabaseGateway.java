package org.example.cinemabackend.cinema.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRepository;
import org.example.cinemabackend.cinema.infrastructure.schema.ScreeningSchema;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class ScreeningDatabaseGateway implements ScreeningRepository {
    private final ScreeningJpaRepository screeningJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Screening> findAll() {
        return screeningJpaRepository.findAll().stream().map(ScreeningSchema::toScreening).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Screening> findByScreeningRoomId(Long id) {
        //      return screeningJpaRepository.findByScreeningRoomId(id).stream().map(ScreeningSchema::toScreening).toList();
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Screening> findById(Long id) {
        return screeningJpaRepository.findById(id).map(ScreeningSchema::toScreening);
    }

    @Override
    @Transactional
    public void save(Screening screeningToUpdate) {
        screeningJpaRepository.save(ScreeningSchema.fromScreening(screeningToUpdate));
    }

    @Override
    public boolean existsByMovieTitle(String title) {
        return screeningJpaRepository.existsByMovieTitle(title);
    }
}
