package org.example.cinemabackend.cinema.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreateContactDetailsRequest(
        @NotBlank @Size(max = 100) String department,
        @NotNull CreateContactTypeRequest contactType
) {
}
