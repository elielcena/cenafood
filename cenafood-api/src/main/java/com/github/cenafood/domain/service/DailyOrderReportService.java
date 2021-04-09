package com.github.cenafood.domain.service;

import com.github.cenafood.domain.filter.DailyOrderFilter;

/**
 * @author elielcena
 *
 */
public interface DailyOrderReportService {

	byte[] generateDailyOrder(DailyOrderFilter filter);

}
