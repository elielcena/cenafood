package com.github.cenafood.api.v1.model.request;

import javax.validation.constraints.Email;
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
@ApiModel("UserRequest")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @ApiModelProperty(example = "ELIEL CENA", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(example = "elielcena@stiweb.com.br", required = true)
    @Email
    @NotBlank
    private String email;

}
