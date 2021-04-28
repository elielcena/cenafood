package com.github.cenafood.api.model.response;

import java.math.BigDecimal;

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
@ApiModel("RestaurantResponse")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponseDTO {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "E'Cena Delivery")
    private String name;

    @ApiModelProperty(example = "10.5")
    private BigDecimal deliveryFee;

    private KitchenResponseDTO kitchen;

    @ApiModelProperty(example = "true")
    private Boolean active;

    @ApiModelProperty(example = "true")
    private Boolean open;

    private AddressResponseDTO address;

}
