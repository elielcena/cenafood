package com.github.cenafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.mapper.UserMapper;
import com.github.cenafood.api.model.response.UserResponseDTO;
import com.github.cenafood.domain.service.RestaurantService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/restaurants/{id}/responsibles")
public class RestaurantUserController {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private UserMapper paymentMapper;

	@GetMapping
	public List<UserResponseDTO> find(@PathVariable Long id) {
		return paymentMapper.toCollectionDTO(restaurantService.findById(id).getUsers());
	}

	@PutMapping("/{idUser}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addPaymentMethodToRestaurant(@PathVariable Long id, @PathVariable Long idUser) {
		restaurantService.addUserToRestaurant(id, idUser);
	}

	@DeleteMapping("/{idUser}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removePaymentMethodToRestaurant(@PathVariable Long id, @PathVariable Long idUser) {
		restaurantService.removeUserToRestaurant(id, idUser);
	}

}
