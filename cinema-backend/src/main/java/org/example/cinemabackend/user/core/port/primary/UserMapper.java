package org.example.cinemabackend.user.core.port.primary;

import org.example.cinemabackend.user.application.dto.response.CinemaManagerTableResponse;
import org.example.cinemabackend.user.application.dto.response.UserResponse;
import org.example.cinemabackend.user.core.domain.User;

public interface UserMapper {
    UserResponse mapUserToUserResponse(User cinemaManager);

    CinemaManagerTableResponse mapUserToCinemaManagerTableResponse(User user);
}
