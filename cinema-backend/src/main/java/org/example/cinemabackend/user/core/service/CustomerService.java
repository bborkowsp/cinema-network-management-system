package org.example.cinemabackend.user.core.service;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.auth.core.port.primary.UserSecurityContextUseCases;
import org.example.cinemabackend.user.application.dto.request.UpdateCustomerProfileRequest;
import org.example.cinemabackend.user.application.dto.response.UserResponse;
import org.example.cinemabackend.user.core.domain.Customer;
import org.example.cinemabackend.user.core.port.primary.CustomerUseCases;
import org.example.cinemabackend.user.core.port.primary.UserMapper;
import org.example.cinemabackend.user.core.port.primary.UserUseCases;
import org.example.cinemabackend.user.core.port.secondary.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerUseCases {
    private final UserSecurityContextUseCases userSecurityContextUseCases;
    private final CustomerRepository customerRepository;
    private final UserMapper userMapper;
    private final UserUseCases userUseCases;

    @Override
    public UserResponse getCustomerProfile() {
        final var email = userSecurityContextUseCases.getCurrentUserEmail();
        final var user = customerRepository.findByEmail(email).orElseThrow();
        return userMapper.mapUserToUserResponse(user);
    }

    @Override
    public void updateCustomerProfile(UpdateCustomerProfileRequest updateCustomerProfileRequest) {
        final var email = userSecurityContextUseCases.getCurrentUserEmail();
        final var userToUpdate = customerRepository.findByEmail(email).orElseThrow();
        updateUserFields(userToUpdate, updateCustomerProfileRequest);
        customerRepository.save(userToUpdate);
    }

    private void updateUserFields(Customer customerToUpdate, UpdateCustomerProfileRequest updateCustomerProfileRequest) {
        customerToUpdate.setFirstName(updateCustomerProfileRequest.firstName());
        customerToUpdate.setLastName(updateCustomerProfileRequest.lastName());
        if (!updateCustomerProfileRequest.email().equals(customerToUpdate.getEmail())) {
            userUseCases.checkIfUserExists(updateCustomerProfileRequest.email());
            customerToUpdate.setEmail(updateCustomerProfileRequest.email());
        }
    }
}
