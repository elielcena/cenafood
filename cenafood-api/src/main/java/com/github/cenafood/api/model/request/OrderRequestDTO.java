package com.github.cenafood.api.model.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

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
@ApiModel("OrderRequest")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    @Valid
    @NotNull
    private RestaurantAbstractRequestDTO restaurant;

    @Valid
    @NotNull
    private AddressRequestDTO address;

    @Valid
    @NotNull
    private PaymentMethodAbstractRequestDTO paymentMethod;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<OrderItemAbstractRequestDTO> orderItems;

    @ApiModel("RestaurantAbstractRequest")
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RestaurantAbstractRequestDTO {

        @ApiModelProperty(example = "1", required = true)
        @NotNull
        private Long id;

    }

    @ApiModel("PaymentMethodAbstractRequest")
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentMethodAbstractRequestDTO {

        @ApiModelProperty(example = "1", required = true)
        @NotNull
        private Long id;

    }

    @ApiModel("OrderItemAbstractRequest")
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemAbstractRequestDTO {

        @ApiModelProperty(example = "1", required = true)
        @NotNull
        private Long idProduct;

        @ApiModelProperty(example = "10", required = true)
        @NotNull
        @PositiveOrZero
        private Integer quantity;

        @ApiModelProperty(example = "No pepper")
        private String note;

    }
}
