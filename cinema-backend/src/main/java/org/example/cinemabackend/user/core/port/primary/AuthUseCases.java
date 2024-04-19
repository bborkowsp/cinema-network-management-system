package org.example.cinemabackend.user.core.port.primary;

import org.example.cinemabackend.user.application.dto.JwtDto;
import org.example.cinemabackend.user.application.dto.LoginUserRequest;
import org.example.cinemabackend.user.application.dto.RegisterUserRequest;

public interface AuthUseCases {
    JwtDto login(LoginUserRequest loginUserRequest);

    void register(RegisterUserRequest registerUserRequest);
}
