package org.example.cinemabackend.cinema.infrastructure.adapter.secondary;

import org.example.cinemabackend.cinema.infrastructure.schema.ProjectionTechnologySchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectionTechnologyJpaRepository extends JpaRepository<ProjectionTechnologySchema, Long> {
    Optional<ProjectionTechnologySchema> findByTechnology(String technology);

    boolean existsByTechnology(String technology);

    void deleteByTechnology(String technology);
}
