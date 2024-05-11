package org.example.cinemabackend.cinema.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ProjectionTechnologyNameResponse(
        @JsonProperty("technology")
        String technology
) {
}
