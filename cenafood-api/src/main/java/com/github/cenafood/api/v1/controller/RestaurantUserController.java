package com.github.cenafood.api.v1.controller;

import static org.apache.commons.lang3.BooleanUtils.isTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.v1.CenaLinks;
import com.github.cenafood.api.v1.mapper.UserMapper;
import com.github.cenafood.api.v1.model.response.UserResponseDTO;
import com.github.cenafood.api.v1.openapi.controller.RestaurantUserControllerOpenApi;
import com.github.cenafood.core.security.SecurityUtil;
import com.github.cenafood.core.security.annotation.CheckSecurity;
import com.github.cenafood.domain.service.RestaurantService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/v1/restaurants/{id}/responsibles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantUserController implements RestaurantUserControllerOpenApi {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CenaLinks cenaLinks;

    @Autowired
    private UserMapper paymentMapper;

    @Autowired
    private SecurityUtil securityUtil;

    @CheckSecurity.NoPreAuthorizeRead
    @GetMapping
    public CollectionModel<UserResponseDTO> find(@PathVariable Long id) {
        CollectionModel<UserResponseDTO> userResponseDTO = paymentMapper.toCollectionModel(restaurantService.findById(id).getUsers())
                .removeLinks().add(cenaLinks.linkToRestaurantUser(id));

        if (isTrue(securityUtil.editUsersRolesPermissions())) {
            userResponseDTO.add(cenaLinks.linkToRestaurantAddUser(id));
            userResponseDTO.getContent().forEach(user -> user.add(cenaLinks.linkToRestauranRemovetUser(id, user.getId())));
        }

        return userResponseDTO;
    }

    @CheckSecurity.Restaurants.Edit
    @PutMapping("/{idUser}")
    public ResponseEntity<Void> addUserToRestaurant(@PathVariable Long id, @PathVariable Long idUser) {
        restaurantService.addUserToRestaurant(id, idUser);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.Edit
    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> removeUserToRestaurant(@PathVariable Long id, @PathVariable Long idUser) {
        restaurantService.removeUserToRestaurant(id, idUser);

        return ResponseEntity.noContent().build();
    }

}
