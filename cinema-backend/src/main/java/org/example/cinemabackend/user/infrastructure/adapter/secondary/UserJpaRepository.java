package org.example.cinemabackend.user.infrastructure.adapter.secondary;

import org.example.cinemabackend.user.infrastructure.scheme.UserSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserSchema, Long> {

    @Query("SELECT u FROM UserSchema u WHERE u.role = 'CINEMA_MANAGER'")
    Page<UserSchema> findAllByRoleCinemaManager(Pageable pageable);

    @Query("SELECT u FROM UserSchema u WHERE u.role = 'CINEMA_NETWORK_MANAGER'")
    Page<UserSchema> findAllByRoleCinemaNetworkManager(Pageable pageable);

    @Query("SELECT u FROM UserSchema u WHERE u.role = 'CINEMA_MANAGER'")
    List<UserSchema> findAllByRoleCinemaManager();

    Optional<UserSchema> findByEmail(String email);

    @Query("SELECT u FROM UserSchema u WHERE u.email = :email AND u.role = 'CINEMA_MANAGER'")
    Optional<UserSchema> findByEmailAndRoleCinemaManager(@Param("email") String email);

    boolean existsByEmail(String email);
}
