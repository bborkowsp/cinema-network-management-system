package org.example.cinemabackend.cinema.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.port.secondary.ProjectionTechnologyRepository;
import org.example.cinemabackend.cinema.infrastructure.schema.ProjectionTechnologySchema;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class ProjectionTechnologyDatabaseGateway implements ProjectionTechnologyRepository {
    private final ProjectionTechnologyJpaRepository projectionTechnologyJpaRepository;


    @Override
    public Optional<ProjectionTechnology> findByTechnology(String technology) {
        return this.projectionTechnologyJpaRepository.findByTechnology(technology).map(ProjectionTechnologySchema::toProjectionTechnology);
    }

    @Override
    public void save(ProjectionTechnology projectionTechnology) {
        this.projectionTechnologyJpaRepository.save(ProjectionTechnologySchema.fromProjectionTechnology(projectionTechnology)).toProjectionTechnology();
    }

    @Override
    public Page<ProjectionTechnology> findAll(Pageable pageable) {
        return this.projectionTechnologyJpaRepository.findAll(pageable).map(ProjectionTechnologySchema::toProjectionTechnology);
    }

    @Override
    public List<ProjectionTechnology> findAll() {
        return this.projectionTechnologyJpaRepository.findAll().stream().map(ProjectionTechnologySchema::toProjectionTechnology).toList();
    }

    @Override
    public void deleteByTechnology(String technology) {
        this.projectionTechnologyJpaRepository.deleteByTechnology(technology);
    }

}
