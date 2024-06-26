package org.example.cinemabackend.user.core.port.secondary;

import org.example.cinemabackend.user.core.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Page<User> findAllCinemaManagers(Pageable pageable);

    Page<User> findAllCinemaNetworkManagers(Pageable pageable);

    List<User> findAllCinemaManagers();

    Optional<User> findByEmail(String email);

    Optional<User> findCinemaManagerByEmail(String email);

    boolean existsByEmail(String email);

    void save(User user);

    void deleteUser(User user);

    Page<User> findAll(Pageable pageable);

}
