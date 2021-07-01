package com.github.cenafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.github.cenafood.api.v1.model.response.PaymentMethodResponseDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author elielcena
 *
 */
@ApiModel("PaymentMethodsResponse")
@Data
public class PaymentMethodResponseOpenApi {

    private PaymentMethodEmbeddedResponseOpenApi embedded;

    private Links _links;

    @ApiModel("PaymentMethodsEmbeddedResponse")
    @Data
    private class PaymentMethodEmbeddedResponseOpenApi {

        private List<PaymentMethodResponseDTO> paymentMethods;

    }

}
