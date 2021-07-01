package com.github.cenafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.github.cenafood.api.v1.controller.StatisticController.StatisticResponseDTO;
import com.github.cenafood.api.v1.model.response.DailyOrderDTO;
import com.github.cenafood.domain.filter.DailyOrderFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author elielcena
 *
 */
@Api(tags = "Statistics")
public interface StatisticControllerOpenApi {

    @ApiOperation(value = "Statistics", hidden = true)
    StatisticResponseDTO statistic();

    @ApiOperation("Search daily order with filters")
    List<DailyOrderDTO> findByDailyOrder(DailyOrderFilter filter);

    ResponseEntity<byte[]> findByDailyOrderPdf(DailyOrderFilter filter);

}
