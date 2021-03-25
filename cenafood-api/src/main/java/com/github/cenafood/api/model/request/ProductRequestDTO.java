package com.github.cenafood.api.model.request;

import java.math.BigDecimal;

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
public class ProductRequestDTO {

	@NotBlank
	private String name;

	@NotBlank
	private String description;

	@PositiveOrZero
	@NotNull
	private BigDecimal price;

	@NotNull
	private Boolean active;

}
