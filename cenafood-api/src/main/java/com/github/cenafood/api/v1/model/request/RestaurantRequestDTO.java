package com.github.cenafood.api.v1.model.request;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

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
@ApiModel("RestaurantRequest")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequestDTO {

    @ApiModelProperty(example = "E'Cena Delivery", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(example = "10.50", required = true)
    @NotNull
    @PositiveOrZero
    private BigDecimal deliveryFee;

    @Valid
    @NotNull
    private KitchenRestaurantRequestDTO kitchen;

    @ApiModelProperty(example = "true", required = true)
    @Valid
    @NotNull
    private Boolean active;

    @Valid
    @NotNull
    private AddressRequestDTO address;

    @ApiModel("KitchenRestaurantRequest")
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class KitchenRestaurantRequestDTO {

        @ApiModelProperty(example = "1", required = true)
        @NotNull
        private Long id;
    }

}
