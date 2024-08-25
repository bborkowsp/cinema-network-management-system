package org.example.cinemabackend.user.core.port.primary;

import org.example.cinemabackend.user.application.dto.JwtDto;
import org.example.cinemabackend.user.application.dto.request.LoginUserRequest;
import org.example.cinemabackend.user.application.dto.request.RegisterUserRequest;

public interface AuthUseCases {
    JwtDto login(LoginUserRequest loginUserRequest);

    void register(RegisterUserRequest registerUserRequest);
}
