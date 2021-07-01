package com.github.cenafood.api.v1.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.github.cenafood.domain.model.OrderStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author elielcena
 *
 */
@Relation(collectionRelation = "orders")
@ApiModel("OrderResponse")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO extends RepresentationModel<OrderResponseDTO> {

    @ApiModelProperty(example = "fcffa16d-d918-4deb-a3e6-cc46e096514")
    private String code;

    @ApiModelProperty(example = "20.00")
    private BigDecimal subtotal;

    @ApiModelProperty(example = "10.00")
    private BigDecimal deliveryfee;

    @ApiModelProperty(example = "30.00")
    private BigDecimal totalPrice;

    private AddressResponseDTO address;

    @ApiModelProperty(example = "CREATED")
    private OrderStatus status;

    @ApiModelProperty(example = "2021-04-21T20:31:58.1029978-03:00")
    private OffsetDateTime createdAt;

    @ApiModelProperty(example = "2021-04-21T20:31:58.1029978-03:00")
    private OffsetDateTime confirmedAt;

    @ApiModelProperty(example = "2021-04-21T20:31:58.1029978-03:00")
    private OffsetDateTime canceledAt;

    @ApiModelProperty(example = "2021-04-21T20:31:58.1029978-03:00")
    private OffsetDateTime deliveredAt;

    private PaymentMethodResponseDTO paymentMethod;

    private RestaurantResponseDTO restaurant;

    private UserResponseDTO customer;

    private List<OrderItemResponseDTO> orderItems;

}
