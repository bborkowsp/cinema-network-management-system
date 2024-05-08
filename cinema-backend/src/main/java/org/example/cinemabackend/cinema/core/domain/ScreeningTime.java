package org.example.cinemabackend.cinema.core.domain;

import java.time.LocalDateTime;

public class ScreeningTime {
    private Long id;
    private ScreeningRoom screeningRoom;
    private LocalDateTime time;

    public ScreeningTime(ScreeningRoom screeningRoom, LocalDateTime time) {
        this.screeningRoom = screeningRoom;
        this.time = time;
    }

    public ScreeningTime(Long id, ScreeningRoom screeningRoom, LocalDateTime time) {
        this.id = id;
        this.screeningRoom = screeningRoom;
        this.time = time;
    }

    public ScreeningRoom getScreeningRoom() {
        return screeningRoom;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Long getId() {
        return id;
    }
}
