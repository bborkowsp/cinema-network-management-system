package org.example.cinemabackend._shared.dto;

import java.util.List;

public record ResponseList<T>(
        List<T> content
) {
}
