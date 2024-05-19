package org.example.cinemabackend.user.core.port.primary;

import org.example.cinemabackend.user.application.dto.response.CinemaManagerResponse;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerTableResponse;
import org.example.cinemabackend.user.application.dto.response.UpdateCinemaManagerRequest;
import org.example.cinemabackend.user.application.dto.response.UserResponse;
import org.example.cinemabackend.user.core.domain.User;

public interface UserMapper {
    UserResponse mapUserToUserResponse(User cinemaManager);

    CinemaManagerTableResponse mapUserToCinemaManagerTableResponse(User user);

    CinemaManagerResponse mapUserToCinemaManagerResponse(User user);

    void updateCinemaManager(User cinemaManagerToUpdate, UpdateCinemaManagerRequest updateCinemaManagerRequest);

}
