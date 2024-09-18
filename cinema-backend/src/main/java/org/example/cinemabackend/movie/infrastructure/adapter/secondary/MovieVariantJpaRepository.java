package org.example.cinemabackend.movie.infrastructure.adapter.secondary;

import org.example.cinemabackend.movie.infrastructure.schema.MovieVariantSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieVariantJpaRepository extends JpaRepository<MovieVariantSchema, Long> {
}
