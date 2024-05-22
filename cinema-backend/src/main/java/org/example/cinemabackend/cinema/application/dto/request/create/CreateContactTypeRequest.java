package org.example.cinemabackend.cinema.application.dto.request.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreateContactTypeRequest(
        @NotBlank @Size(max = 15) String phoneNumber,
        @Email @NotBlank String email
) {
}
