package org.example.cinemabackend.user.application.adapter.primary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.application.dto.request.UpdateCustomerProfileRequest;
import org.example.cinemabackend.user.application.dto.response.UserResponse;
import org.example.cinemabackend.user.core.port.primary.CustomerUseCases;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerUseCases customerUseCases;

    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    ResponseEntity<UserResponse> getCustomerProfile() {
        final var customerProfile = customerUseCases.getCustomerProfile();
        return ResponseEntity.ok(customerProfile);
    }

    @PatchMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    ResponseEntity<Void> updateCustomerProfile(@RequestBody UpdateCustomerProfileRequest updateCustomerProfileRequest) {
        customerUseCases.updateCustomerProfile(updateCustomerProfileRequest);
        return ResponseEntity.noContent().build();
    }
}
