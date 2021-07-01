package com.github.cenafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.github.cenafood.api.model.response.RestaurantResponseDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author elielcena
 *
 */
@ApiModel("RestaurantsResponse")
@Data
public class RestaurantResponseOpenApi {

    private RestaurantEmbeddedResponseOpenApi embedded;

    private Links _links;

    @ApiModel("RestaurantsEmbeddedResponse")
    @Data
    private class RestaurantEmbeddedResponseOpenApi {

        private List<RestaurantResponseDTO> restaurants;

    }

}
