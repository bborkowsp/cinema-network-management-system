package org.example.cinemabackend.cinema.core.domain;

import java.time.LocalDateTime;

public class ScreeningTime {
    private ScreeningRoom screeningRoom;
    private LocalDateTime time;

    public ScreeningTime(ScreeningRoom screeningRoom, LocalDateTime time) {
        this.screeningRoom = screeningRoom;
        this.time = time;
    }

    public ScreeningRoom getScreeningRoom() {
        return screeningRoom;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
