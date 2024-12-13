package org.example.cinemabackend.auth.core.port.primary;

import org.example.cinemabackend.auth.application.dto.JwtDto;
import org.example.cinemabackend.auth.application.dto.request.LoginUserRequest;

public interface UserAuthUseCases {

    JwtDto login(LoginUserRequest loginUserRequest);

    JwtDto refreshToken(JwtDto jwtDto);
}
