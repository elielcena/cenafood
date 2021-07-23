package com.github.cenafood.api.v1.model.response;

import java.math.BigDecimal;

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
@Relation(collectionRelation = "orderItems")
@ApiModel("OrderItemResponse")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDTO extends RepresentationModel<OrderItemResponseDTO> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "10")
    private BigDecimal unitPrice;

    @ApiModelProperty(example = "10")
    private BigDecimal totalPrice;

    @ApiModelProperty(example = "1")
    private Integer quantity;

    @ApiModelProperty(example = "No pepper")
    private String note;

}
