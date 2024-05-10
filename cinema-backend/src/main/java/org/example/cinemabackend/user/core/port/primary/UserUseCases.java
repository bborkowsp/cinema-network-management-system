package org.example.cinemabackend.user.core.port.primary;

import org.example.cinemabackend.user.application.dto.response.UserTableResponse;

import java.util.List;

public interface UserUseCases {
    List<UserTableResponse> getUsers();

}
