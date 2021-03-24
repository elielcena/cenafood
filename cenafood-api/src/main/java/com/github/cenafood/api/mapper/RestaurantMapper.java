package com.github.cenafood.api.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.model.request.RestaurantRequestDTO;
import com.github.cenafood.api.model.response.RestaurantResponseDTO;
import com.github.cenafood.domain.model.City;
import com.github.cenafood.domain.model.Kitchen;
import com.github.cenafood.domain.model.Restaurant;

/**
 * @author elielcena
 *
 */
@Component
public class RestaurantMapper {

	@Autowired
	private ModelMapper modelMapper;

	public RestaurantResponseDTO toDTO(Restaurant restaurant) {
		return modelMapper.map(restaurant, RestaurantResponseDTO.class);
	}

	public List<RestaurantResponseDTO> toCollectionDTO(List<Restaurant> restaurant) {
		return restaurant.stream().map(rest -> toDTO(rest)).collect(Collectors.toList());
	}

	public Restaurant toDomainEntity(RestaurantRequestDTO restaurant) {
		return modelMapper.map(restaurant, Restaurant.class);
	}

	public void copyToDomainEntity(RestaurantRequestDTO restaurantRequest, Restaurant restaurant) {
		/*
		 * Do not remove "new", because to reference a new kitchen and city it is
		 * necessary to set a new object. To avoid org.hibernate.HibernateException:
		 * identifier of an instance of com.algaworks.algafood.domain.model.Cozinha was
		 * altered from {} {}
		 */
		restaurant.setKitchen(new Kitchen());

		if (Optional.ofNullable(restaurant.getAddress()).isPresent())
			restaurant.getAddress().setCity(new City());

		modelMapper.map(restaurantRequest, restaurant);
	}

}
