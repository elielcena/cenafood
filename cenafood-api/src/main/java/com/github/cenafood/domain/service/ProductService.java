package com.github.cenafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.cenafood.domain.exception.ResourceNotFoundException;
import com.github.cenafood.domain.model.Product;
import com.github.cenafood.domain.model.Restaurant;
import com.github.cenafood.domain.repository.ProductRepository;

/**
 * @author elielcena
 *
 */
@Service
public class ProductService {

	private static final String MSG_RESOURCE_NOT_FOUND = "There is no product register with code %d";

	@Autowired
	private ProductRepository productRepository;

	public Product findById(Long id, Long idRestaurant) {
		return productRepository.findById(id, idRestaurant)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(MSG_RESOURCE_NOT_FOUND, id)));
	}

	public List<Product> findRestaurant(Restaurant restaurant) {
		return productRepository.findByRestaurant(restaurant);
	}

	public Product save(Product product) {
		return productRepository.save(product);
	}

}
