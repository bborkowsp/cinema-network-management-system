package org.example.cinemabackend.user.core.port.secondary;

import org.example.cinemabackend.user.core.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    List<Customer> findAllUnverifiedCustomers();

    Optional<Customer> findByEmail(String email);

    boolean existsByEmail(String email);

    void save(Customer user);

    void deleteCustomer(Customer customer);
}
