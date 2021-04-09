package com.github.cenafood.domain.filter;

import java.time.LocalDate;

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
public class DailyOrderFilter {

	private Long idRestaurant;

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate startDate;

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate endDate;

}
