package org.example.cinemabackend.user.core.port.primary;

import org.example.cinemabackend.user.application.dto.request.CreateCinemaManagerRequest;
import org.example.cinemabackend.user.application.dto.request.CreateUserRequest;
import org.example.cinemabackend.user.application.dto.request.UpdateCinemaManagerRequest;
import org.example.cinemabackend.user.application.dto.request.UpdateUserRequest;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerResponse;
import org.example.cinemabackend.user.application.dto.response.CinemaManagerTableResponse;
import org.example.cinemabackend.user.application.dto.response.UserResponse;
import org.example.cinemabackend.user.core.domain.User;

public interface UserMapper {
    UserResponse mapUserToUserResponse(User cinemaManager);

    CinemaManagerTableResponse mapUserToCinemaManagerTableResponse(User user);

    CinemaManagerResponse mapUserToCinemaManagerResponse(User user);

    User mapCreateCinemaManagerRequestToUser(CreateCinemaManagerRequest createCinemaManagerRequest);

    User mapCreateUserRequestToUser(CreateUserRequest createCinemaManagerRequest);

    void mapUpdateCinemaManagerRequestToUser(User cinemaManagerToUpdate, UpdateCinemaManagerRequest updateCinemaManagerRequest);

    void mapUpdateCinemaNetworkManagerRequestToUser(User cinemaNetworkManagerToUpdate, UpdateUserRequest updateCinemaManagerRequest);
}
