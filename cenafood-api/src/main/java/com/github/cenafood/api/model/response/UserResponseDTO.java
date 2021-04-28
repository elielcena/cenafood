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
@ApiModel("UserResponse")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "ELIEL CENA")
    private String name;

    @ApiModelProperty(example = "elielcena@stiweb.com.br")
    private String email;

}
