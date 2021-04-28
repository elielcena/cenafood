package com.github.cenafood.api.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.github.cenafood.api.mapper.RestaurantMapper;
import com.github.cenafood.api.model.request.RestaurantRequestDTO;
import com.github.cenafood.api.model.response.RestaurantResponseDTO;
import com.github.cenafood.api.openapi.controller.RestaurantControllerOpenApi;
import com.github.cenafood.domain.model.Restaurant;
import com.github.cenafood.domain.service.RestaurantService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController implements RestaurantControllerOpenApi {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantMapper mapper;

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDTO>> findAll() {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .body(mapper.toCollectionDTO(restaurantService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .body(mapper.toDTO(restaurantService.findById(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantResponseDTO save(@RequestBody @Valid RestaurantRequestDTO restaurant) {
        return mapper.toDTO(restaurantService.save(mapper.toDomainEntity(restaurant)));
    }

    @PutMapping("/{id}")
    public RestaurantResponseDTO update(@PathVariable Long id,
            @RequestBody @Valid RestaurantRequestDTO restaurantRequest) {
        Restaurant restaurant = restaurantService.findById(id);
        mapper.copyToDomainEntity(restaurantRequest, restaurant);
        return mapper.toDTO(restaurantService.save(restaurant));
    }

    @PutMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long id) {
        restaurantService.activate(id);
    }

    @DeleteMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivate(@PathVariable Long id) {
        restaurantService.activate(id);
    }

    @PutMapping("/{id}/opening")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void opening(@PathVariable Long id) {
        restaurantService.opening(id);
    }

    @PutMapping("/{id}/closure")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void closure(@PathVariable Long id) {
        restaurantService.closure(id);
    }

}
