package com.github.cenafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.github.cenafood.domain.model.City;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author elielcena
 *
 */
@ApiModel("CitiesResponse")
@Data
public class CityResponseOpenApi {

    private CityEmbeddedResponseOpenApi embedded;

    private Links _links;

    @ApiModel("CitiesEmbeddedResponse")
    @Data
    private class CityEmbeddedResponseOpenApi {

        private List<City> cities;

    }

}
