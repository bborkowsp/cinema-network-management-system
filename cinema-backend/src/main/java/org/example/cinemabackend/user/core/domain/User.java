package org.example.cinemabackend.user.core.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

public class User implements UserDetails {
    private static final String ROLE_AUTHORITY_PREFIX = "ROLE_";
    private Long id;
    private Role role;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private boolean isAccountVerified = false;
    private LocalDateTime createdAt;

    public User(String firstName, String lastName, String email, String passwordHash, Role role, LocalDateTime createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.createdAt = createdAt;
    }

    public User(Long id, String firstName, String lastName, String email, String passwordHash, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public User(Long id, String firstName, String lastName, String email, String passwordHash, Role role, boolean isAccountVerified, LocalDateTime createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.isAccountVerified = isAccountVerified;
        this.createdAt = createdAt;
    }

    public User(String firstName, String lastName, String email, String passwordHash, Role role) {
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }

    public boolean getIsAccountVerified() {
        return isAccountVerified;
    }

    public void setIsAccountVerified(boolean b) {
        this.isAccountVerified = b;
    }

    public void setAccountVerified(boolean accountVerified) {
        isAccountVerified = accountVerified;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    public void setPassword(String encodedPassword) {
        this.passwordHash = encodedPassword;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
