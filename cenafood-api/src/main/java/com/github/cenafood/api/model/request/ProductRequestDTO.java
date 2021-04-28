package com.github.cenafood.api.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

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
@ApiModel("ProductRequest")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    @ApiModelProperty(example = "COCA-COLA 2L", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(example = "COCA-COLA 2L", required = true)
    @NotBlank
    private String description;

    @ApiModelProperty(example = "10.00", required = true)
    @PositiveOrZero
    @NotNull
    private BigDecimal price;

    @ApiModelProperty(example = "true", required = true)
    @NotNull
    private Boolean active;

}
