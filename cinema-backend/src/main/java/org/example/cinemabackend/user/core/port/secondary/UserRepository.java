package org.example.cinemabackend.user.core.port.secondary;

import org.example.cinemabackend.user.core.domain.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    void save(User user);

}
