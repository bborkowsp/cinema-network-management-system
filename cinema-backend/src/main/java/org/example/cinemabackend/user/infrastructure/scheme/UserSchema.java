package org.example.cinemabackend.user.infrastructure.scheme;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.cinemabackend.cinema.infrastructure.schema.CinemaSchema;
import org.example.cinemabackend.user.core.domain.Role;
import org.example.cinemabackend.user.core.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSchema implements UserDetails {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Role role;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 60)
    private String firstName;
    @NotBlank
    @Column(nullable = false, length = 60)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String passwordHash;

    @OneToOne(mappedBy = "cinemaManager")
    private CinemaSchema cinema;

    public static UserSchema fromUser(User user) {
        return UserSchema.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .passwordHash(user.getPasswordHash())
                .role(user.getRole())
                .cinema(user.getCinema() != null ? CinemaSchema.fromCinema(user.getCinema()) : null)
                .build();
    }


    public User toUser() {
        User user = new User(
                this.id,
                this.firstName,
                this.lastName,
                this.email,
                this.passwordHash,
                this.role
        );
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final var roleAuthorityName = role.name();
        final var roleAuthority = new SimpleGrantedAuthority(roleAuthorityName);
        return Set.of(roleAuthority);
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

