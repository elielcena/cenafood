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

    public boolean isAuthenticated() {
        return getAuthentication().isAuthenticated();
    }

    public Boolean hasAuthority(String authorityName) {
        return getAuthentication().getAuthorities()
                .stream()
                .anyMatch(authority -> authority.getAuthority().equals(authorityName));
    }

    public Boolean scopeWrite() {
        return hasAuthority("SCOPE_WRITE");
    }

    public Boolean scopeRead() {
        return hasAuthority("SCOPE_READ");
    }

    public Boolean noPreAuthorizeWrite() {
        return scopeWrite() && isAuthenticated();
    }

    public Boolean noPreAuthorizeRead() {
        return scopeRead() && isAuthenticated();
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

    public Boolean userSameAsAuthenticated(Long idUser) {
        return getIdUser() != null && idUser != null && getIdUser().equals(idUser);
    }

    public Boolean manageOrder(String code) {
        return hasAuthority("SCOPE_WRITE") && (hasAuthority("MANAGE_ORDERS") || manageRestaurantOrder(code));
    }

    public Boolean consultRestaurant() {
        return scopeRead() && isAuthenticated();
    }

    public Boolean manageRestaurantRegistration() {
        return scopeWrite() && hasAuthority("EDIT_RESTAURANTS");
    }

    public Boolean manageRestaurantOperation(Long idRestaurant) {
        return scopeWrite() && (hasAuthority("EDIT_RESTAURANTS") || manageRestaurant(idRestaurant));
    }

    public Boolean consultUsersRolesPermissions() {
        return scopeRead() && hasAuthority("CONSULT_USERS_ROLES_PERMISSIONS");
    }

    public Boolean editUsersRolesPermissions() {
        return scopeWrite() && hasAuthority("EDIT_USERS_ROLES_PERMISSIONS");
    }

    public Boolean consultOrders(Long idUser, Long idRestaurant) {
        return scopeRead() && (hasAuthority("CONSULT_ORDERS") || userSameAsAuthenticated(idUser) || manageRestaurant(idRestaurant));
    }

    public Boolean consultStatistic() {
        return scopeRead() && hasAuthority("GENERATE_REPORTS");
    }

}
