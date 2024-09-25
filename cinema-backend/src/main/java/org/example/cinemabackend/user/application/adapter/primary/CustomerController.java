package org.example.cinemabackend.user.application.adapter.primary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.application.dto.request.UpdateCustomerProfileRequest;
import org.example.cinemabackend.user.application.dto.request.UpdatePasswordRequest;
import org.example.cinemabackend.user.application.dto.response.UserResponse;
import org.example.cinemabackend.user.core.port.primary.UserUseCases;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final UserUseCases userUseCases;

    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    ResponseEntity<UserResponse> getCustomerProfile() {
        final var customerProfile = userUseCases.getCustomerProfile();
        return ResponseEntity.ok(customerProfile);
    }

    @PatchMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    ResponseEntity<Void> updateCustomerProfile(@RequestBody UpdateCustomerProfileRequest updateCustomerProfileRequest) {
        userUseCases.updateCustomerProfile(updateCustomerProfileRequest);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-password")
    @PreAuthorize("hasRole('CUSTOMER')")
    ResponseEntity<Void> updateCustomerProfile(@RequestBody UpdatePasswordRequest updateCustomerProfileRequest) {
        userUseCases.updatePassword(updateCustomerProfileRequest);
        return ResponseEntity.noContent().build();
    }
}
