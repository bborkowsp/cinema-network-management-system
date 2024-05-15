package org.example.cinemabackend.user.infrastructure.adapter.secondary;

import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.core.domain.User;
import org.example.cinemabackend.user.core.port.secondary.UserRepository;
import org.example.cinemabackend.user.infrastructure.scheme.UserSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class UserDatabaseGateway implements UserRepository {

    private final UserJpaRepository userJpaRepository;


    @Override
    public Optional<User> findByEmail(String email) {
        return this.userJpaRepository.findByEmail(email).map(UserSchema::toUser);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userJpaRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public void save(User user) {
        final var userSchema = UserSchema.fromUser(user);
        this.userJpaRepository.save(userSchema);
    }


    @Override
    public Page<User> findAllCinemaManagers(Pageable pageable) {
        return this.userJpaRepository.findAllByRoleCinemaManager(pageable).map(UserSchema::toUser);
    }

    @Override
    public List<User> findAllCinemaManagers() {
        return this.userJpaRepository.findAllByRoleCinemaManager().stream().map(UserSchema::toUser).toList();
    }

    @Override
    public Optional<User> findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email) {
        return this.userJpaRepository.findByFirstNameAndLastNameAndEmail(firstName, lastName, email).map(UserSchema::toUser);
    }

    @Override
    public Optional<User> findCinemaManagerByEmail(String email) {
        return this.userJpaRepository.findByEmailAndRoleCinemaManager(email).map(UserSchema::toUser);
    }
}
