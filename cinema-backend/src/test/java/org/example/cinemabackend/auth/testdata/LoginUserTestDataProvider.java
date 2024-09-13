package org.example.cinemabackend.auth.testdata;

import org.example.cinemabackend.auth.application.dto.request.LoginUserRequest;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

public class LoginUserTestDataProvider {

    private static final String EMAIL = "user@example.com";
    private static final String PASSWORD = "password";
    private static final String INVALID_PASSWORD = RandomStringUtils.random(5);

    public static LoginUserRequest createLoginUserRequest() {
        return new LoginUserRequest(
                EMAIL,
                PASSWORD
        );
    }

    public static LoginUserRequest createLoginUserRequestWithInvalidPassword() {
        return new LoginUserRequest(
                EMAIL,
                INVALID_PASSWORD
        );
    }
}
