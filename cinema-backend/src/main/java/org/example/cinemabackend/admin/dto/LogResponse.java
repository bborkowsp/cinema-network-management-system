package org.example.cinemabackend.admin.dto;

import java.sql.Timestamp;

public record LogResponse(
        Timestamp timestamp,
        String level,
        String logger,
        String message
) {
}
