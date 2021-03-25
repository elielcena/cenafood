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
public class ProductResponseDTO {

	private Long id;

	private String name;

	private String description;

	private BigDecimal price;

	private Boolean active;

}
