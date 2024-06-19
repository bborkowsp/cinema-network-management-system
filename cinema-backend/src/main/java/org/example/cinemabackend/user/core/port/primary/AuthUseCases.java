package org.example.cinemabackend.user.core.port.primary;

import org.example.cinemabackend.user.application.dto.JwtDto;
import org.example.cinemabackend.user.application.dto.request.LoginUserRequest;

public interface AuthUseCases {
    JwtDto login(LoginUserRequest loginUserRequest);
}
