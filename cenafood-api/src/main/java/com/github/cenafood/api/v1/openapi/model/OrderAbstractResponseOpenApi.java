package com.github.cenafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.github.cenafood.api.v1.model.response.OrderAbstractResponseDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author elielcena
 *
 */
@ApiModel("PageOrdersAbstractResponse")
@Data
public class OrderAbstractResponseOpenApi {

    private OrderEmbeddedResponseOpenApi embedded;

    private Links _links;

    private PageResponseOpenApi page;

    @ApiModel("OrdersEmbeddedResponse")
    @Data
    private class OrderEmbeddedResponseOpenApi {

        private List<OrderAbstractResponseDTO> orders;

    }

}
