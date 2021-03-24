package com.github.cenafood.api.model.response;

import javax.validation.constraints.NotBlank;

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
public class PaymentMethodResponseDTO {

	private Long id;

	@NotBlank
	private String description;

}
