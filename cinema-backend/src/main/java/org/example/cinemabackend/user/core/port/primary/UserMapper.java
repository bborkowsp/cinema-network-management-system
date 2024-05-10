package org.example.cinemabackend.user.core.port.primary;

import org.example.cinemabackend.user.application.dto.response.UserResponse;
import org.example.cinemabackend.user.application.dto.response.UserTableResponse;
import org.example.cinemabackend.user.core.domain.User;

public interface UserMapper {
    UserResponse mapUserToUserResponse(User cinemaManager);

    UserTableResponse mapUserToUserTableResponse(User user);
}
