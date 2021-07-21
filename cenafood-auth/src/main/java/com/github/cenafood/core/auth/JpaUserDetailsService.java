package com.github.cenafood.core.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.cenafood.domain.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário inexistente ou senha inválida"));
        // User.builder()
        // .id(1L)
        // .email("elielsc99@gmail.com")
        // .password("$2y$12$9gS..go4f2gn6aQSOsde5.bvNGdLpvF0zezidabCODnEcLCHW5iSi")
        // .name("Eliel Cena")
        // .build();
        return new AuthUser(user);
    }

}
