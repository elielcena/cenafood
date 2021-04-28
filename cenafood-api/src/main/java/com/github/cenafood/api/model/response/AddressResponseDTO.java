package com.github.cenafood.api.model.response;

import java.io.Serializable;

import com.github.cenafood.domain.model.City;

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
@ApiModel("AddressResponse")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(example = "15828-000")
    private String zipCode;

    @ApiModelProperty(example = "Street Vereador Amilcar Roveri")
    private String street;

    @ApiModelProperty(example = "361")
    private String number;

    @ApiModelProperty(example = "House")
    private String complement;

    @ApiModelProperty(example = "Center")
    private String district;

    private City city;

}
