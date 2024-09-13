package org.example.cinemabackend.auth.testdata;

import org.example.cinemabackend.auth.application.dto.request.RegisterUserRequest;
import org.example.cinemabackend.user.core.domain.Role;

public class RegisterUserTestDataProvider {
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL = "user@example.com";
    private static final String PASSWORD = "password";

    public static RegisterUserRequest createRegisterUserRequest(Role role) {
        return new RegisterUserRequest(
                FIRST_NAME,
                LAST_NAME,
                EMAIL,
                PASSWORD,
                role
        );
    }
}
