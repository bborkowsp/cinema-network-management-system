package org.example.cinemabackend.auth.core.service;

import org.example.cinemabackend.auth.core.port.primary.UserSecurityContextUseCases;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
class UserSecurityContextService implements UserSecurityContextUseCases {
    @Override
    public String getCurrentUserEmail() {
        Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();
        return (String) authenticationToken.getPrincipal();
    }
}
