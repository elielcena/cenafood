package com.github.cenafood.api.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
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
public class OrderAbstractResponseDTO {

	private UUID code;

	private BigDecimal subtotal;

	private BigDecimal deliveryfee;

	private BigDecimal totalPrice;

	private OrderStatus status;

	private OffsetDateTime createdAt;

	private RestaurantAbstractResponseDTO restaurant;

	private UserResponseDTO customer;

	@Builder
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class RestaurantAbstractResponseDTO {

		private Long id;

		private String name;

	}

}
