package com.github.cenafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Data;

/**
 * @author elielcena
 *
 */
@Builder
@Data
@Embeddable
public class Adress {

	@Column(name = "ZIPCODE")
	private String zipCode;

	@Column(name = "STREET")
	private String street;

	@Column(name = "NUMBER")
	private String number;

	@Column(name = "COMPLEMENT")
	private String complement;

	@Column(name = "DISTRICT")
	private String district;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDCITY")
	private City city;

}
