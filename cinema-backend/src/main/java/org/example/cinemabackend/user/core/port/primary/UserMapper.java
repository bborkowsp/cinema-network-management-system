package org.example.cinemabackend.user.core.port.primary;

import org.example.cinemabackend.user.application.dto.UserResponse;
import org.example.cinemabackend.user.core.domain.User;

public interface UserMapper {
    UserResponse mapUserToUserResponse(User cinemaManager);
}
