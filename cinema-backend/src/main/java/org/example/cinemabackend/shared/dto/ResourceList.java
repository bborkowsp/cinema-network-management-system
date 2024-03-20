package org.example.cinemabackend.shared.dto;

import java.util.List;

public record ResourceList<T>(
        List<T> content
) {
}
