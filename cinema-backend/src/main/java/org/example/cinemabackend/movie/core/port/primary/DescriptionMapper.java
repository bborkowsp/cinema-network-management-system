package org.example.cinemabackend.movie.core.port.primary;

import org.example.cinemabackend.movie.application.dto.response.DescriptionResponse;
import org.example.cinemabackend.movie.core.domain.Description;

public interface DescriptionMapper {
    DescriptionResponse mapDescriptionToDescriptionResponse(Description description);
}
