package com.github.cenafood.core.auth;

import java.util.Collections;

import com.github.cenafood.domain.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    private Long idUser;

    private String fullName;

    public AuthUser(User user) {
        super(user.getEmail(), user.getPassword(), Collections.emptyList());

        this.idUser = user.getId();
        this.fullName = user.getName();
    }
}
