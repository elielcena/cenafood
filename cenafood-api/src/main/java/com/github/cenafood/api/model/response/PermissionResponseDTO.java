package com.github.cenafood.api.model.response;

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
@ApiModel("PermissionResponse")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResponseDTO {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "EDIT")
    private String name;

    @ApiModelProperty(example = "EDIT RESTAURANT")
    private String description;

}
