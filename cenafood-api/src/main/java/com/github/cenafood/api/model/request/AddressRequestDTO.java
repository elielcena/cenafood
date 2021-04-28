package com.github.cenafood.api.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@ApiModel("AddressRequest")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDTO {

    @ApiModelProperty(example = "15828-000", required = true)
    @NotBlank
    private String zipCode;

    @ApiModelProperty(example = "Street Vereador Amilcar Roveri", required = true)
    @NotBlank
    private String street;

    @ApiModelProperty(example = "361", required = true)
    @NotBlank
    private String number;

    @ApiModelProperty(example = "House", required = true)
    @NotBlank
    private String complement;

    @ApiModelProperty(example = "Center", required = true)
    @NotBlank
    private String district;

    @Valid
    @NotNull
    private CityAddressRequestDTO city;

    @ApiModel("CityAddressRequest")
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CityAddressRequestDTO {

        @ApiModelProperty(example = "1", required = true)
        @NotNull
        private Long id;
    }

}
