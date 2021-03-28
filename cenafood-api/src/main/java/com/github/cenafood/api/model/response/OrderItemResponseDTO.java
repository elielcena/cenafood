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
public class OrderItemResponseDTO {

	private Long id;

	private BigDecimal unitPrice;

	private BigDecimal totalPrice;

	private Integer quantity;

	private String note;

}
