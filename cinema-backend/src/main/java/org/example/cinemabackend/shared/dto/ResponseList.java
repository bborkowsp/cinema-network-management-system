package org.example.cinemabackend.shared.dto;

import java.util.List;

public record ResponseList<T>(
        List<T> content
) {
}
