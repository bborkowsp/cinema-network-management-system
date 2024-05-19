package org.example.cinemabackend.cinema.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.domain.ScreeningRoom;
import org.example.cinemabackend.cinema.core.port.secondary.ScreeningRoomRepository;
import org.example.cinemabackend.cinema.infrastructure.schema.ScreeningRoomSchema;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class ScreeningRoomDatabaseGateway implements ScreeningRoomRepository {

    private final ScreeningRoomJpaRepository screeningRoomJpaRepository;

    @Override
    public Optional<ScreeningRoom> findByName(String name) {
        return screeningRoomJpaRepository.findByName(name).map(ScreeningRoomSchema::toScreeningRoom);
    }
}
