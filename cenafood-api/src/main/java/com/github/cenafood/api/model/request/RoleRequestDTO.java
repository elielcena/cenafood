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
@ApiModel("RoleRequest")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequestDTO {

    @ApiModelProperty(example = "CUSTOMER", required = true)
    @NotBlank
    private String name;

}
