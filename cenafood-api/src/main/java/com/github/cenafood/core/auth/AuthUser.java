package com.github.cenafood.core.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.github.cenafood.domain.model.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    private Long idUser;

    private String fullName;

    public AuthUser(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);

        this.idUser = user.getId();
        this.fullName = user.getName();
    }
}
