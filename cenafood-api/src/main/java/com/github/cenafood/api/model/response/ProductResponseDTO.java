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
@ApiModel("ProductResponse")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "COCA-COLA 2L")
    private String name;

    @ApiModelProperty(example = "COCA-COLA 2L")
    private String description;

    @ApiModelProperty(example = "10.00")
    private BigDecimal price;

    @ApiModelProperty(example = "true")
    private Boolean active;

}
