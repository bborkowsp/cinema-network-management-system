package org.example.cinemabackend.projectiontechnology.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ProjectionTechnologyNameResponse(
        @JsonProperty("technology")
        String technology
) {
}
