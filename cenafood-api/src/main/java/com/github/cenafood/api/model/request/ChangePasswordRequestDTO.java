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
@ApiModel("ChangePasswordRequest")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequestDTO {

    @ApiModelProperty(example = "123", required = true, position = 1)
    @NotBlank
    private String currentPassword;

    @ApiModelProperty(example = "321", required = true, position = 2)
    @NotBlank
    private String newPassword;

    @ApiModelProperty(example = "321", required = true, position = 3)
    @NotBlank
    private String confirmPassword;

}
