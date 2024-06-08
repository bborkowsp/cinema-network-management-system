package org.example.cinemabackend.cinema.core.port.secondary;

import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProjectionTechnologyRepository {

    Page<ProjectionTechnology> findProjectionTechnologyPage(Pageable pageable);

    List<ProjectionTechnology> findAll();

    Optional<ProjectionTechnology> findByTechnology(String technology);

    boolean existsByTechnology(String technology);

    void save(ProjectionTechnology projectionTechnology);

    void deleteByTechnology(String technology);
}