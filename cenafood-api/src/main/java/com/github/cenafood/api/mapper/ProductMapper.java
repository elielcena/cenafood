package com.github.cenafood.api.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.model.request.ProductRequestDTO;
import com.github.cenafood.api.model.response.ProductResponseDTO;
import com.github.cenafood.domain.model.Product;

/**
 * @author elielcena
 *
 */
@Component
public class ProductMapper {

	@Autowired
	private ModelMapper modelMapper;

	public ProductResponseDTO toDTO(Product product) {
		return modelMapper.map(product, ProductResponseDTO.class);
	}

	public List<ProductResponseDTO> toCollectionDTO(Collection<Product> product) {
		return product.stream().map(rest -> toDTO(rest)).collect(Collectors.toList());
	}

	public Product toDomainEntity(ProductRequestDTO product) {
		return modelMapper.map(product, Product.class);
	}

	public void copyToDomainEntity(ProductRequestDTO productRequest, Product product) {
		modelMapper.map(productRequest, product);
	}

}
