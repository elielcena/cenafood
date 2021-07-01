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
@Relation(collectionRelation = "orders")
@ApiModel("OrderCreatedResponse")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedResponseDTO extends RepresentationModel<OrderCreatedResponseDTO> {

    @ApiModelProperty(example = "fcffa16d-d918-4deb-a3e6-cc46e096514")
    private String code;

}
