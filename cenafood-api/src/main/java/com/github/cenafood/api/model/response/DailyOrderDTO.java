package com.github.cenafood.api.model.response;

import java.math.BigDecimal;
import java.util.Date;

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
public class DailyOrderDTO {

	private Date date;

	private Long totalOrder;

	private BigDecimal totalBilled;

}
