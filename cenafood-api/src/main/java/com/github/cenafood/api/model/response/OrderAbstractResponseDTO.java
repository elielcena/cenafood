package com.github.cenafood.api.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.github.cenafood.domain.model.OrderStatus;

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
@ApiModel("OrderAbstractResponse")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAbstractResponseDTO {

    @ApiModelProperty(example = "fcffa16d-d918-4deb-a3e6-cc46e0965103")
    private String code;

    @ApiModelProperty(example = "105.50")
    private BigDecimal subtotal;

    @ApiModelProperty(example = "15.50")
    private BigDecimal deliveryfee;

    @ApiModelProperty(example = "121.00")
    private BigDecimal totalPrice;

    @ApiModelProperty(example = "CREATED")
    private OrderStatus status;

    @ApiModelProperty(example = "2021-04-21T20:31:58.1029978-03:00")
    private OffsetDateTime createdAt;

    private RestaurantAbstractResponseDTO restaurant;

    private UserResponseDTO customer;

    @ApiModel("RestaurantAbstractResponse")
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RestaurantAbstractResponseDTO {

        @ApiModelProperty(example = "1")
        private Long id;

        @ApiModelProperty(example = "E'Cena Delivery")
        private String name;

    }

}
