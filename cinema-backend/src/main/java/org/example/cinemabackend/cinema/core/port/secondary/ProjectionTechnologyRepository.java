package org.example.cinemabackend.cinema.core.port.secondary;

import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProjectionTechnologyRepository {
    Optional<ProjectionTechnology> findByTechnology(String technology);

    void save(ProjectionTechnology projectionTechnology);

    Page<ProjectionTechnology> findAll(Pageable pageable);

    List<ProjectionTechnology> findAll();

    void deleteByTechnology(String technology);


}
