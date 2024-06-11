package org.example.cinemabackend.cinema.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.Screening;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRoomRepository;
import org.example.cinemabackend.cinema.infrastructure.schema.ScreeningRoomSchema;
import org.example.cinemabackend.cinema.infrastructure.schema.ScreeningSchema;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class ScreeningRoomDatabaseGateway implements ScreeningRoomRepository {
    private final ScreeningRoomJpaRepository screeningRoomJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<ScreeningRoom> findByName(String name) {
        return screeningRoomJpaRepository.findByName(name).map(ScreeningRoomSchema::toScreeningRoom);
    }

    @Override
    @Transactional
    public void save(ScreeningRoom screeningRoom) {
        this.screeningRoomJpaRepository.save(ScreeningRoomSchema.fromScreeningRoom(screeningRoom));
    }

    @Override
    public Optional<ScreeningRoom> findByContainsScreening(Screening screening) {
        return screeningRoomJpaRepository.findByRepertoryContains(ScreeningSchema.fromScreening(screening))
                .map(ScreeningRoomSchema::toScreeningRoom);
    }

    @Override
    public Optional<ScreeningRoom> findByRepertoryContains(Screening screening) {
        return screeningRoomJpaRepository.findByRepertoryContains(ScreeningSchema.fromScreening(screening))
                .map(ScreeningRoomSchema::toScreeningRoom);
    }
}
