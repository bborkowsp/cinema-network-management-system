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
    @Transactional(readOnly = true)
    public Page<User> findAllCinemaManagers(Pageable pageable) {
        return this.userJpaRepository.findAllByRoleCinemaManager(pageable).map(UserSchema::toUser);
    }

    @Override
    public Page<User> findAllCinemaNetworkManagers(Pageable pageable) {
        return this.userJpaRepository.findAllByRoleCinemaNetworkManager(pageable).map(UserSchema::toUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllCinemaManagers() {
        return this.userJpaRepository.findAllByRoleCinemaManager().stream().map(UserSchema::toUser).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return this.userJpaRepository.findByEmail(email).map(UserSchema::toUser);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findCinemaManagerByEmail(String email) {
        return this.userJpaRepository.findByEmailAndRoleCinemaManager(email).map(UserSchema::toUser);
    }

    @Override
    @Transactional(readOnly = true)
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
    public void deleteUser(User user) {
        this.userJpaRepository.delete(UserSchema.fromUser(user));
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return this.userJpaRepository.findAll(pageable).map(UserSchema::toUser);
    }
}
