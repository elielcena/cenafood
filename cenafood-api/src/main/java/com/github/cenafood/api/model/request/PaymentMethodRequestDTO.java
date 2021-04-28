package com.github.cenafood.api.model.request;

import javax.validation.constraints.NotBlank;

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
@ApiModel("PaymentMethodRequest")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodRequestDTO {

    @ApiModelProperty(example = "MONEY")
    @NotBlank
    private String description;

}
