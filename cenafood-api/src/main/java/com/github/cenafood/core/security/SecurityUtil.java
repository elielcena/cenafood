package com.github.cenafood.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

/**
 * @author elielcena
 *
 */
@Component
public class SecurityUtil {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getIdUser() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();
        return jwt.getClaim("idUser");
    }

}
