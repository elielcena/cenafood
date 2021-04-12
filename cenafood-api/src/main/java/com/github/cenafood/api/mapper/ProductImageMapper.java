package com.github.cenafood.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.model.response.ProductImageResponseDTO;
import com.github.cenafood.domain.model.ProductImage;

/**
 * @author elielcena
 *
 */
@Component
public class ProductImageMapper {

	@Autowired
	private ModelMapper modelMapper;

	public ProductImageResponseDTO toDTO(ProductImage productImage) {
		return modelMapper.map(productImage, ProductImageResponseDTO.class);
	}

}
