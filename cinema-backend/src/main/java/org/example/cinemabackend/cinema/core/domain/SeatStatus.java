package org.example.cinemabackend.cinema.core.domain;

import java.time.LocalDateTime;

public class SeatStatus {
    private Status status;
    private LocalDateTime statusStart;
    private LocalDateTime statusEnd;

    public SeatStatus(Status status, LocalDateTime statusStart, LocalDateTime statusEnd) {
        this.status = status;
        this.statusStart = statusStart;
        this.statusEnd = statusEnd;
    }


    public Status getStatus() {
        return status;
    }

    public LocalDateTime getStatusStart() {
        return statusStart;
    }

    public LocalDateTime getStatusEnd() {
        return statusEnd;
    }
}
