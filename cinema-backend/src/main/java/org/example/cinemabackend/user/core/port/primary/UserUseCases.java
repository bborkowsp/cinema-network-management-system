package org.example.cinemabackend.user.core.port.primary;

import org.example.cinemabackend.user.application.dto.request.CreateCinemaManagerRequest;
import org.example.cinemabackend.user.application.dto.request.CreateCinemaNetworkManagerRequest;
import org.example.cinemabackend.user.application.dto.request.UpdateCinemaManagerRequest;
import org.example.cinemabackend.user.application.dto.request.UpdateCinemaNetworkManagerRequest;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerResponse;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerTableResponse;
import org.example.cinemabackend.user.application.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserUseCases {
    Page<CinemaManagerTableResponse> getCinemaManagers(Pageable pageable);

    Page<UserResponse> getUsers(Pageable pageable);

    UserResponse getUser(String email);

    CinemaManagerResponse getCinemaManager(String email);

    void createCinemaManager(CreateCinemaManagerRequest createCinemaManagerRequest);

    void createCinemaNetworkManager(CreateCinemaNetworkManagerRequest createCinemaManagerRequest);

    void updateCinemaManager(String email, UpdateCinemaManagerRequest updateCinemaManagerRequest);

    void updateCinemaNetworkManager(String email, UpdateCinemaNetworkManagerRequest updateCinemaManagerRequest);

    void deleteCinemaManager(String email);

    void deleteUser(String email);
}
