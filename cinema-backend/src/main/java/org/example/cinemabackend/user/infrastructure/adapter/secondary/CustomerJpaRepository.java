package org.example.cinemabackend.user.infrastructure.adapter.secondary;

import org.example.cinemabackend.user.infrastructure.schema.CustomerSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerSchema, Long> {

    List<CustomerSchema> findAllByIsAccountVerifiedFalse();

    Optional<CustomerSchema> findByEmail(String email);

    boolean existsByEmail(String email);
}
