package org.example.cinemabackend.movie.infrastructure.adapter.secondary;

import org.example.cinemabackend.movie.infrastructure.schema.MovieSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieJpaRepository extends JpaRepository<MovieSchema, Long> {
    @Query("SELECT COUNT(m) > 0 FROM MovieSchema m JOIN m.projectionTechnologies pt WHERE pt.technology = :technology")
    boolean existsByProjectionTechnologyTechnology(@Param("technology") String technology);
}