package com.github.cenafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.github.cenafood.api.v1.model.response.ProductResponseDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author elielcena
 *
 */
@ApiModel("ProductsResponse")
@Data
public class ProductResponseOpenApi {

    private ProductEmbeddedResponseOpenApi embedded;

    private Links _links;

    @ApiModel("ProductsEmbeddedResponse")
    @Data
    private class ProductEmbeddedResponseOpenApi {

        private List<ProductResponseDTO> products;

    }

}
