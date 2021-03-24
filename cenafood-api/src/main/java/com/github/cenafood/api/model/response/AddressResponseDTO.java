package com.github.cenafood.api.model.response;

import com.github.cenafood.domain.model.City;

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
public class AddressResponseDTO {

	private String zipCode;

	private String street;

	private String number;

	private String complement;

	private String district;

	private City city;

}
