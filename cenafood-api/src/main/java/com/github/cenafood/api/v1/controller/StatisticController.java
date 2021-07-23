package com.github.cenafood.api.v1.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.v1.CenaLinks;
import com.github.cenafood.api.v1.model.response.DailyOrderDTO;
import com.github.cenafood.api.v1.openapi.controller.StatisticControllerOpenApi;
import com.github.cenafood.core.security.annotation.CheckSecurity;
import com.github.cenafood.domain.filter.DailyOrderFilter;
import com.github.cenafood.domain.service.DailyOrderReportService;
import com.github.cenafood.domain.service.DailyOrderService;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/v1/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticController implements StatisticControllerOpenApi {

    @Autowired
    private DailyOrderService dailyOrderService;

    @Autowired
    private DailyOrderReportService dailyOrderReportService;

    @Autowired
    private CenaLinks cenaLinks;

    @CheckSecurity.Statistic.Consult
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public StatisticResponseDTO statistic() {
        return StatisticResponseDTO.builder().build().add(cenaLinks.linkToStatisticDailyOrder().withRel("dailyOrder"));
    }

    @CheckSecurity.Statistic.Consult
    @GetMapping(path = "/daily-order", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DailyOrderDTO> findByDailyOrder(DailyOrderFilter filter) {
        return dailyOrderService.findByFilter(filter);
    }

    @CheckSecurity.Statistic.Consult
    @GetMapping(path = "/daily-order", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> findByDailyOrderPdf(DailyOrderFilter filter) {
        byte[] bytesPdf = dailyOrderReportService.generateDailyOrder(filter);

        var fileName = "daily-orders-" + LocalDateTime.now();
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s.pdf", fileName));

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).headers(headers).body(bytesPdf);
    }

    @ApiModel("StatisticResponse")
    @Builder
    public static class StatisticResponseDTO extends RepresentationModel<StatisticResponseDTO> {
    }

}
