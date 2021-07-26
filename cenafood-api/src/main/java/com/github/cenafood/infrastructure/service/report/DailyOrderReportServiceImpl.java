package com.github.cenafood.infrastructure.service.report;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.cenafood.domain.filter.DailyOrderFilter;
import com.github.cenafood.domain.service.DailyOrderReportService;
import com.github.cenafood.domain.service.DailyOrderService;
import com.github.cenafood.infrastructure.service.exception.ReportException;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author elielcena
 *
 */
@Service
public class DailyOrderReportServiceImpl implements DailyOrderReportService {

    private static final String ERROR_GENERATE_REPORT_DAILY_ORDER = "Unable to generate daily order report";

    @Autowired
    private ExportPdf exportPdf;

    @Autowired
    private DailyOrderService dailyOrderService;

    @Override
    public byte[] generateDailyOrder(DailyOrderFilter filter) {
        try {
            var dailyOrder = dailyOrderService.findByFilter(filter);
            var dataSource = new JRBeanCollectionDataSource(dailyOrder);

            return exportPdf.generate("/reports/daily-order.jasper", new HashMap<>(), dataSource);
        } catch (Exception e) {
            throw new ReportException(ERROR_GENERATE_REPORT_DAILY_ORDER, e);
        }
    }

}
