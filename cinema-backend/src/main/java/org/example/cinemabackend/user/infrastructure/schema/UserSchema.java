package org.example.cinemabackend.user.infrastructure.schema;

import jakarta.persistence.*;
import lombok.*;
import org.example.cinemabackend.user.core.domain.Role;
import org.example.cinemabackend.user.core.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSchema implements UserDetails {
    private static final String ROLE_AUTHORITY_PREFIX = "ROLE_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private Boolean isAccountVerified = false;

    @Column
    private LocalDateTime createdAt;

    public static UserSchema fromUser(User user) {
        return UserSchema.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .passwordHash(user.getPasswordHash())
                .role(user.getRole())
                .isAccountVerified(user.getIsAccountVerified())
                .createdAt(user.getCreatedAt())
                .build();
    }


    public User toUser() {
        return new User(
                this.id,
                this.firstName,
                this.lastName,
                this.email,
                this.passwordHash,
                this.role,
                this.isAccountVerified,
                this.createdAt
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final var roleAuthorityName = ROLE_AUTHORITY_PREFIX + role.name();
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
