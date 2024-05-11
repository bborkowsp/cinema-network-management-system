package org.example.cinemabackend.user.infrastructure.adapter.secondary;

import org.example.cinemabackend.user.infrastructure.scheme.UserSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserSchema, Long> {

    Optional<UserSchema> findByEmail(String email);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);

    @Query("SELECT u FROM UserSchema u WHERE u.role = 'CINEMA_MANAGER'")
    List<UserSchema> findAllByRoleCinemaManager();

    @Query("SELECT u FROM UserSchema u WHERE u.role = 'CINEMA_MANAGER'")
    Page<UserSchema> findAllByRoleCinemaManager(Pageable pageable);

}
