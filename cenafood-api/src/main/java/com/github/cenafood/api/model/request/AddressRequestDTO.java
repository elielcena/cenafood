package com.github.cenafood.api.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class AddressRequestDTO {

	@NotBlank
	private String zipCode;

	@NotBlank
	private String street;

	@NotBlank
	private String number;

	@NotBlank
	private String complement;

	@NotBlank
	private String district;

	@Valid
	@NotNull
	private CityAddressRequestDTO city;

	@Builder
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CityAddressRequestDTO {

		@NotNull
		private Long id;
	}

}
