package com.github.cenafood.api.model.response;

import java.io.Serializable;

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
public class AddressResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String zipCode;

	private String street;

	private String number;

	private String complement;

	private String district;

	private City city;

}
