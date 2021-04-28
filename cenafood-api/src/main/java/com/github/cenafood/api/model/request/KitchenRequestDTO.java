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
@ApiModel("KitchenRequest")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KitchenRequestDTO {

    @ApiModelProperty(example = "Eastern")
    @NotBlank
    private String name;

}
