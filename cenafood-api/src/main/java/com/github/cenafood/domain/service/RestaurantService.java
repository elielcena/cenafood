package com.github.cenafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.cenafood.domain.exception.ResourceNotFoundException;
import com.github.cenafood.domain.model.Kitchen;
import com.github.cenafood.domain.model.Restaurant;
import com.github.cenafood.domain.repository.RestaurantRepository;

/**
 * @author elielcena
 *
 */
@Service
public class RestaurantService {

	private static final String MSG_RESTAURANT_NOT_FOUND = "There is no restaurant registration with code %d";

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private KitchenService kitchenService;

	public List<Restaurant> findAll() {
		return restaurantRepository.findAll();
	}

	public Restaurant findById(Long idRestaurant) {
		return restaurantRepository.findById(idRestaurant).orElseThrow(
				() -> new ResourceNotFoundException(String.format(MSG_RESTAURANT_NOT_FOUND, idRestaurant)));
	}

	public Restaurant save(Restaurant restaurant) {
		Kitchen kitchen = kitchenService.findById(restaurant.getKitchen().getId());

		restaurant.setKitchen(kitchen);

		return restaurantRepository.save(restaurant);
	}

	public Restaurant update(Long id, Restaurant restaurant) {
		Restaurant currentRestaurant = findById(id);

		BeanUtils.copyProperties(restaurant, currentRestaurant, "id", "paymentMethods", "adress", "createdAt",
				"products");

		return save(currentRestaurant);
	}

}
