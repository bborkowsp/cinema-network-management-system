package org.example.cinemabackend.user.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.core.domain.Customer;
import org.example.cinemabackend.user.core.port.secondary.CustomerRepository;
import org.example.cinemabackend.user.infrastructure.schema.CustomerSchema;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class CustomerJpaDatabaseGateway implements CustomerRepository {
    private final CustomerJpaRepository customerJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAllUnverifiedCustomers() {
        return this.customerJpaRepository.findAllByIsAccountVerifiedFalse().stream().map(CustomerSchema::toCustomer).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findByEmail(String email) {
        return this.customerJpaRepository.findByEmail(email).map(CustomerSchema::toCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return this.customerJpaRepository.existsByEmail(email);
    }

    @Override
    public void save(Customer user) {
        final var customerSchema = CustomerSchema.fromCustomer(user);
        this.customerJpaRepository.save(customerSchema);
    }

    @Override
    @Transactional
    public void deleteCustomer(Customer customer) {
        this.customerJpaRepository.delete(CustomerSchema.fromCustomer(customer));
    }
}
