package com.github.cenafood.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.model.response.DailyOrderDTO;
import com.github.cenafood.api.openapi.controller.StatisticControllerOpenApi;
import com.github.cenafood.domain.filter.DailyOrderFilter;
import com.github.cenafood.domain.service.DailyOrderReportService;
import com.github.cenafood.domain.service.DailyOrderService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticController implements StatisticControllerOpenApi {

    @Autowired
    private DailyOrderService dailyOrderService;

    @Autowired
    private DailyOrderReportService dailyOrderReportService;

    @GetMapping(path = "/daily-order", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DailyOrderDTO> findByDailyOrder(DailyOrderFilter filter) {
        return dailyOrderService.findByFilter(filter);
    }

    @GetMapping(path = "/daily-order", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> findByDailyOrderPdf(DailyOrderFilter filter) {
        byte[] bytesPdf = dailyOrderReportService.generateDailyOrder(filter);

        var fileName = "daily-orders-" + LocalDateTime.now();
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s.pdf", fileName));

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).headers(headers).body(bytesPdf);
    }

}
