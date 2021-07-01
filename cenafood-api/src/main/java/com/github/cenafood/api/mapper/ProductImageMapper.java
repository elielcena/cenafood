package com.github.cenafood.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.controller.RestaurantProductImageController;
import com.github.cenafood.api.model.response.ProductImageResponseDTO;
import com.github.cenafood.domain.model.ProductImage;

/**
 * @author elielcena
 *
 */
@Component
public class ProductImageMapper extends RepresentationModelAssemblerSupport<ProductImage, ProductImageResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    public ProductImageMapper() {
        super(RestaurantProductImageController.class, ProductImageResponseDTO.class);
    }

    @Override
    public ProductImageResponseDTO toModel(ProductImage productImage) {
        return modelMapper.map(productImage, ProductImageResponseDTO.class);
    }

}
