package com.github.cenafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.github.cenafood.api.model.response.KitchenResponseDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author elielcena
 *
 */
@ApiModel("KitchensResponse")
@Data
public class KitchenResponseOpenApi {

    private KitchenEmbeddedResponseOpenApi embedded;

    private Links _links;

    @ApiModel("KitchensEmbeddedResponse")
    @Data
    private class KitchenEmbeddedResponseOpenApi {

        private List<KitchenResponseDTO> kitchens;

    }

}
