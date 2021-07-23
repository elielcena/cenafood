package com.github.cenafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.v1.CenaLinks;
import com.github.cenafood.api.v1.mapper.ProductMapper;
import com.github.cenafood.api.v1.model.request.ProductRequestDTO;
import com.github.cenafood.api.v1.model.response.ProductResponseDTO;
import com.github.cenafood.api.v1.openapi.controller.RestaurantProductControllerOpenApi;
import com.github.cenafood.core.security.anotation.CheckSecurity;
import com.github.cenafood.domain.model.Product;
import com.github.cenafood.domain.model.Restaurant;
import com.github.cenafood.domain.service.ProductService;
import com.github.cenafood.domain.service.RestaurantService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/v1/restaurants/{id}/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantProductController implements RestaurantProductControllerOpenApi {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CenaLinks cenaLinks;

    @CheckSecurity.Restaurants.Consult
    @GetMapping
    public CollectionModel<ProductResponseDTO> find(@PathVariable Long id) {
        CollectionModel<ProductResponseDTO> productResponseDTO =
                productMapper.toCollectionModel(productService.findRestaurant(restaurantService.findById(id)))
                        .add(cenaLinks.linkToProducts(id).withSelfRel());

        productResponseDTO.getContent().forEach(product -> product.add(cenaLinks.linkToProduct(id, product.getId()))
                .add(cenaLinks.linkToProducts(id)));

        return productResponseDTO;
    }

    @CheckSecurity.Restaurants.Consult
    @GetMapping("/{idProduct}")
    public ProductResponseDTO findById(@PathVariable Long id, @PathVariable Long idProduct) {
        Product product = productService.findById(idProduct, id);

        return productMapper.toModel(product)
                .add(cenaLinks.linkToProduct(id, idProduct))
                .add(cenaLinks.linkToProducts(id));
    }

    @CheckSecurity.Restaurants.Manage
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO save(@PathVariable Long id, @RequestBody @Valid ProductRequestDTO productRequest) {
        Restaurant restaurant = restaurantService.findById(id);

        Product product = productMapper.toDomainEntity(productRequest);
        product.setRestaurant(restaurant);

        return productMapper.toModel(productService.save(product));
    }

    @CheckSecurity.Restaurants.Manage
    @PutMapping("/{idProduct}")
    public ProductResponseDTO update(@PathVariable Long id, @PathVariable Long idProduct,
            @RequestBody @Valid ProductRequestDTO productRequest) {
        Product product = productService.findById(idProduct, id);

        productMapper.copyToDomainEntity(productRequest, product);

        return productMapper.toModel(productService.save(product));
    }

}
