package com.github.cenafood.domain.service;

import java.util.List;

import com.github.cenafood.api.model.response.DailyOrderDTO;
import com.github.cenafood.domain.filter.DailyOrderFilter;

/**
 * @author elielcena
 *
 */
public interface DailyOrderService {

	List<DailyOrderDTO> findByFilter(DailyOrderFilter filter);

}
