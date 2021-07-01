package com.github.cenafood.api.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author elielcena
 *
 */
@Relation(collectionRelation = "paymentMethods")
@ApiModel("PaymentMethodResponse")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodResponseDTO extends RepresentationModel<PaymentMethodResponseDTO> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "MONEY")
    private String description;

}
