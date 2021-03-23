package com.github.cenafood.api.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.domain.model.Restaurant;
import com.github.cenafood.domain.service.RestaurantService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@GetMapping
	public List<Restaurant> findAll() {
		return restaurantService.findAll();
	}

	@GetMapping("/{id}")
	public Restaurant findById(@PathVariable Long id) {
		return restaurantService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurant save(@RequestBody @Valid Restaurant restaurant) {
		return restaurantService.save(restaurant);
	}

	@PutMapping("/{id}")
	public Restaurant update(@PathVariable Long id, @RequestBody @Valid Restaurant restaurant) {
		return restaurantService.update(id, restaurant);
	}

	@PatchMapping("/{id}")
	public Restaurant partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		return restaurantService.partialUpdate(id, fields);
	}

}
