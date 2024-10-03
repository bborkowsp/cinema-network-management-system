package org.example.cinemabackend.user.core.port.primary;

import org.example.cinemabackend.user.application.dto.request.UpdateCustomerProfileRequest;
import org.example.cinemabackend.user.application.dto.response.UserResponse;

public interface CustomerUseCases {
    UserResponse getCustomerProfile();

    void updateCustomerProfile(UpdateCustomerProfileRequest updateCustomerProfileRequest);
}
