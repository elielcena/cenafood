package com.github.cenafood.infrastructure.service.report;

import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.github.cenafood.infrastructure.service.exception.ReportException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

/**
 * @author elielcena
 *
 */
@Component
public class ExportPdf {

    public byte[] generate(String path, Map<String, Object> parameters, JRDataSource dataSource) {
        try {
            var inputStream = this.getClass().getResourceAsStream(path);

            parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));

            var jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException("Unable to generate report", e);
        }
    }

}
