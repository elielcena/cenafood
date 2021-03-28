package com.github.cenafood.api.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import com.github.cenafood.domain.model.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elielcena
 *
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {

	private UUID code;

	private BigDecimal subtotal;

	private BigDecimal deliveryfee;

	private BigDecimal totalPrice;

	private AddressResponseDTO address;

	private OrderStatus status;

	private OffsetDateTime createdAt;

	private OffsetDateTime confirmedAt;

	private OffsetDateTime canceledAt;

	private OffsetDateTime deliveredAt;

	private PaymentMethodResponseDTO paymentMethod;

	private RestaurantResponseDTO restaurant;

	private UserResponseDTO customer;

	private List<OrderItemResponseDTO> orderItems;

}
