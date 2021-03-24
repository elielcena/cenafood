package com.github.cenafood.api.model.request;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

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
public class RestaurantRequestDTO {

	@NotBlank
	private String name;

	@NotNull
	@PositiveOrZero
	private BigDecimal deliveryFee;

	@Valid
	@NotNull
	private KitchenRestaurantRequestDTO kitchen;

	@Valid
	@NotNull
	private Boolean active;

	@Valid
	@NotNull
	private AddressRequestDTO address;

	@Builder
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class KitchenRestaurantRequestDTO {

		@NotNull
		private Long id;
	}

}
