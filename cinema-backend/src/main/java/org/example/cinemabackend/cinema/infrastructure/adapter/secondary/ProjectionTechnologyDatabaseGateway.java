package org.example.cinemabackend.cinema.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.cinema.core.port.secondary.ProjectionTechnologyRepository;
import org.example.cinemabackend.cinema.infrastructure.schema.ProjectionTechnologySchema;
import org.example.cinemabackend.movie.core.domain.ProjectionTechnology;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class ProjectionTechnologyDatabaseGateway implements ProjectionTechnologyRepository {

    private final ProjectionTechnologyJpaRepository projectionTechnologyJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ProjectionTechnology> findProjectionTechnologyPage(Pageable pageable) {
        final var projectionTechnologies = this.projectionTechnologyJpaRepository.findAll(pageable);
        return projectionTechnologies.map(ProjectionTechnologySchema::toProjectionTechnology);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectionTechnology> findAll() {
        final var projectionTechnologies = this.projectionTechnologyJpaRepository.findAll();
        return projectionTechnologies.stream().map(ProjectionTechnologySchema::toProjectionTechnology).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectionTechnology> findByTechnology(@NonNull String technology) {
        final var projectionTechnology = this.projectionTechnologyJpaRepository.findByTechnology(technology);
        return projectionTechnology.map(ProjectionTechnologySchema::toProjectionTechnology);
    }

    @Override
    @Transactional
    public void save(@NonNull ProjectionTechnology projectionTechnology) {
        final var projectionTechnologySchema = ProjectionTechnologySchema.fromProjectionTechnology(projectionTechnology);
        this.projectionTechnologyJpaRepository.save(projectionTechnologySchema);
    }

    @Override
    @Transactional
    public void deleteByTechnology(@NonNull String technology) {
        this.projectionTechnologyJpaRepository.deleteByTechnology(technology);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByTechnology(@NonNull String technology) {
        return this.projectionTechnologyJpaRepository.existsByTechnology(technology);
    }
}
