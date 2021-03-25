package com.github.cenafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.mapper.ProductMapper;
import com.github.cenafood.api.model.request.ProductRequestDTO;
import com.github.cenafood.api.model.response.ProductResponseDTO;
import com.github.cenafood.domain.model.Product;
import com.github.cenafood.domain.model.Restaurant;
import com.github.cenafood.domain.service.ProductService;
import com.github.cenafood.domain.service.RestaurantService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/restaurants/{id}/products")
public class RestaurantProductController {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductMapper productMapper;

	@GetMapping
	public List<ProductResponseDTO> find(@PathVariable Long id) {
		return productMapper.toCollectionDTO(productService.findRestaurant(restaurantService.findById(id)));
	}

	@GetMapping("/{produtoId}")
	public ProductResponseDTO buscar(@PathVariable Long id, @PathVariable Long produtoId) {
		Product product = productService.findById(id, produtoId);

		return productMapper.toDTO(product);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductResponseDTO save(@PathVariable Long id, @RequestBody @Valid ProductRequestDTO productRequest) {
		Restaurant restaurant = restaurantService.findById(id);

		Product product = productMapper.toDomainEntity(productRequest);
		product.setRestaurant(restaurant);

		return productMapper.toDTO(productService.save(product));
	}

	@PutMapping("/{idProduct}")
	public ProductResponseDTO update(@PathVariable Long id, @PathVariable Long idProduct,
			@RequestBody @Valid ProductRequestDTO productRequest) {
		Product product = productService.findById(idProduct, id);

		productMapper.copyToDomainEntity(productRequest, product);

		return productMapper.toDTO(productService.save(product));
	}

}
