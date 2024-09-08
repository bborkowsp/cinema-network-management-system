package org.example.cinemabackend.user.application.adapter.primary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.application.dto.response.UserResponse;
import org.example.cinemabackend.user.core.port.primary.UserUseCases;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class CustomerController {
    private final UserUseCases userUseCases;

    @GetMapping("/customer/{email}")
    ResponseEntity<UserResponse> getCustomerProfile(@PathVariable String email) {
        final var customerProfile = userUseCases.getCustomerProfile(email);
        return ResponseEntity.ok(customerProfile);
    }
}
