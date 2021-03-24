package com.github.cenafood.api.model.response;

import java.math.BigDecimal;

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
public class RestaurantResponseDTO {

	private Long id;

	private String name;

	private BigDecimal deliveryFee;

	private KitchenResponseDTO kitchen;
	
	private Boolean active;

}
