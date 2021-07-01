package com.github.cenafood.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.controller.RestaurantProductController;
import com.github.cenafood.api.model.request.ProductRequestDTO;
import com.github.cenafood.api.model.response.ProductResponseDTO;
import com.github.cenafood.domain.model.Product;

/**
 * @author elielcena
 *
 */
@Component
public class ProductMapper extends RepresentationModelAssemblerSupport<Product, ProductResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    public ProductMapper() {
        super(RestaurantProductController.class, ProductResponseDTO.class);
    }

    @Override
    public ProductResponseDTO toModel(Product product) {
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    public Product toDomainEntity(ProductRequestDTO product) {
        return modelMapper.map(product, Product.class);
    }

    public void copyToDomainEntity(ProductRequestDTO productRequest, Product product) {
        modelMapper.map(productRequest, product);
    }

}
