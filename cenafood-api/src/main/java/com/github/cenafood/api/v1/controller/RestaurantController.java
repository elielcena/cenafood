package com.github.cenafood.api.v1.controller;

import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.v1.mapper.RestaurantMapper;
import com.github.cenafood.api.v1.model.request.RestaurantRequestDTO;
import com.github.cenafood.api.v1.model.response.RestaurantResponseDTO;
import com.github.cenafood.api.v1.openapi.controller.RestaurantControllerOpenApi;
import com.github.cenafood.core.security.anotation.CheckSecurity;
import com.github.cenafood.domain.model.Restaurant;
import com.github.cenafood.domain.service.RestaurantService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/v1/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController implements RestaurantControllerOpenApi {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantMapper mapper;

    @CheckSecurity.Restaurants.Consult
    @GetMapping
    public ResponseEntity<CollectionModel<RestaurantResponseDTO>> findAll() {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .body(mapper.toCollectionModel(restaurantService.findAll()));
    }

    @CheckSecurity.Restaurants.Consult
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .body(mapper.toModel(restaurantService.findById(id)));
    }

    @CheckSecurity.Restaurants.Edit
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantResponseDTO save(@RequestBody @Valid RestaurantRequestDTO restaurant) {
        return mapper.toModel(restaurantService.save(mapper.toDomainEntity(restaurant)));
    }

    @CheckSecurity.Restaurants.Edit
    @PutMapping("/{id}")
    public RestaurantResponseDTO update(@PathVariable Long id,
            @RequestBody @Valid RestaurantRequestDTO restaurantRequest) {
        Restaurant restaurant = restaurantService.findById(id);
        mapper.copyToDomainEntity(restaurantRequest, restaurant);
        return mapper.toModel(restaurantService.save(restaurant));
    }

    @CheckSecurity.Restaurants.Edit
    @PutMapping("/{id}/active")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        restaurantService.activate(id);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.Edit
    @DeleteMapping("/{id}/active")
    public ResponseEntity<Void> inactivate(@PathVariable Long id) {
        restaurantService.inactivate(id);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.Manage
    @PutMapping("/{id}/opening")
    public ResponseEntity<Void> opening(@PathVariable Long id) {
        restaurantService.opening(id);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.Manage
    @PutMapping("/{id}/closure")
    public ResponseEntity<Void> closure(@PathVariable Long id) {
        restaurantService.closure(id);

        return ResponseEntity.noContent().build();
    }

}
