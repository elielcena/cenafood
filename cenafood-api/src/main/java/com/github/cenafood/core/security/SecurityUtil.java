package com.github.cenafood.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.github.cenafood.domain.repository.OrderRepository;
import com.github.cenafood.domain.repository.RestaurantRepository;

/**
 * @author elielcena
 *
 */
@Component
public class SecurityUtil {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getIdUser() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();
        return jwt.getClaim("idUser");
    }

    public Boolean manageRestaurant(Long idRestaurant) {
        if (idRestaurant == null) {
            return false;
        }

        return restaurantRepository.existsUsers(idRestaurant, getIdUser());
    }

    public Boolean manageRestaurantOrder(String code) {
        return orderRepository.isManagedByUser(code, getIdUser());
    }

    public Boolean userIsAuthenticated(Long idUser) {
        return getIdUser() != null && idUser != null && getIdUser().equals(idUser);
    }

}
