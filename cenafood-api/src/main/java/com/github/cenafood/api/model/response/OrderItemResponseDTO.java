package com.github.cenafood.api.model.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elielcena
 *
 */
@ApiModel("OrderItemResponse")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDTO {

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
