package org.example.cinemabackend.auth.core.port.primary;

import org.example.cinemabackend.auth.application.dto.JwtDto;
import org.example.cinemabackend.auth.application.dto.request.LoginUserRequest;
import org.example.cinemabackend.auth.application.dto.request.RegisterUserRequest;
import org.example.cinemabackend.auth.application.dto.request.ResetPasswordRequest;

public interface AuthUseCases {

    String getCurrentUserEmail();

    JwtDto login(LoginUserRequest loginUserRequest);

    void register(RegisterUserRequest registerUserRequest);

    void processRequestForPasswordReset(String email);

    void resetPassword(ResetPasswordRequest resetPasswordRequest);

    void verifyAccount(String email);
}
