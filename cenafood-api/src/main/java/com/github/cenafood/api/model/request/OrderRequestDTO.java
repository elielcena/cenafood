package com.github.cenafood.api.model.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

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

	@Builder
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class RestaurantAbstractRequestDTO {

		@NotNull
		private Long id;

	}

	@Builder
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class PaymentMethodAbstractRequestDTO {

		@NotNull
		private Long id;

	}

	@Builder
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class OrderItemAbstractRequestDTO {

		@NotNull
		private Long idProduct;

		@NotNull
		@PositiveOrZero
		private Integer quantity;

		private String note;

	}
}
