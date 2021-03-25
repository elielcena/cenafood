package com.github.cenafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.cenafood.domain.exception.BusinessException;
import com.github.cenafood.domain.exception.ResourceNotFoundException;
import com.github.cenafood.domain.model.City;
import com.github.cenafood.domain.model.Kitchen;
import com.github.cenafood.domain.model.PaymentMethod;
import com.github.cenafood.domain.model.Restaurant;
import com.github.cenafood.domain.repository.RestaurantRepository;

/**
 * @author elielcena
 *
 */
@Service
public class RestaurantService {

	private static final String MSG_RESOURCE_NOT_FOUND = "There is no restaurant registration with code %d";

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private KitchenService kitchenService;

	@Autowired
	private CityService cityService;

	@Autowired
	private PaymentMethodService paymentMethodService;

	public List<Restaurant> findAll() {
		return restaurantRepository.findAll();
	}

	public Restaurant findById(Long id) {
		return restaurantRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(MSG_RESOURCE_NOT_FOUND, id)));
	}

	public Restaurant save(Restaurant restaurant) {
		try {
			Long idKitchen = restaurant.getKitchen().getId();
			Long idCity = restaurant.getAddress().getCity().getId();

			Kitchen kitchen = kitchenService.findById(idKitchen);
			City city = cityService.findById(idCity);

			restaurant.setKitchen(kitchen);
			restaurant.getAddress().setCity(city);

			return restaurantRepository.save(restaurant);
		} catch (ResourceNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	public void activate(Long id) {
		save(findById(id).activate());
	}

	public void inactivate(Long id) {
		save(findById(id).inactivate());
	}

	public void opening(Long id) {
		save(findById(id).opening());
	}

	public void closure(Long id) {
		save(findById(id).closure());
	}

	public void addPaymentMethodToRestaurant(Long idRestaurant, Long idPaymentMethod) {
		addOrRemovePaymentMethod(Boolean.TRUE, idRestaurant, idPaymentMethod);
	}

	public void removePaymentMethodToRestaurant(Long idRestaurant, Long idPaymentMethod) {
		addOrRemovePaymentMethod(Boolean.FALSE, idRestaurant, idPaymentMethod);
	}

	private void addOrRemovePaymentMethod(Boolean isAdd, Long idRestaurant, Long idPaymentMethod) {
		Restaurant restaurant = findById(idRestaurant);
		PaymentMethod paymentMethod = paymentMethodService.findById(idPaymentMethod);

		restaurant.addOrRemovePaymentMethod(isAdd, paymentMethod);

		save(restaurant);
	}

}
