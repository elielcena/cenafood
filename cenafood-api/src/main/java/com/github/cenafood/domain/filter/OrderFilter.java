package com.github.cenafood.domain.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

/**
 * @author elielcena
 *
 */

@Getter
@Setter
public class OrderFilter {

	private Long idCustomer;

	private Long idRestaurant;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime creationStartDate;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime creationEndDate;

}
