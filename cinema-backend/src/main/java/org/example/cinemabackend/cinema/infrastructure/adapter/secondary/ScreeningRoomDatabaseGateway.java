package org.example.cinemabackend.cinema.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRoomRepository;
import org.example.cinemabackend.cinema.infrastructure.schema.ScreeningRoomSchema;
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
}
