package org.example.cinemabackend.user.core.port.primary;

import org.example.cinemabackend.user.application.dto.response.CinemaManagerResponse;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerTableResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserUseCases {
    Page<CinemaManagerTableResponse> getCinemaManagers(Pageable pageable);

    List<CinemaManagerResponse> getCinemaManagers();

}
