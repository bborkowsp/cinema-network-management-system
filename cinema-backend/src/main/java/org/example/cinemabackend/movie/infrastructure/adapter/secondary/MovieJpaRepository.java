package org.example.cinemabackend.movie.infrastructure.adapter.secondary;

import org.example.cinemabackend.movie.infrastructure.schema.MovieSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieJpaRepository extends JpaRepository<MovieSchema, Long> {

    Optional<MovieSchema> findByTitle(String title);

    void deleteByTitle(String title);
}