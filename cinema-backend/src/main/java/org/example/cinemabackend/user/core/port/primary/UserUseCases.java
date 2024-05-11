package org.example.cinemabackend.user.core.port.primary;

import org.example.cinemabackend.user.application.dto.response.CinemaManagerTableResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserUseCases {
    Page<CinemaManagerTableResponse> getCinemaManagers(Pageable pageable);

}
