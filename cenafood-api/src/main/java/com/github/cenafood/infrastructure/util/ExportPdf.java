package com.github.cenafood.infrastructure.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import com.github.cenafood.infrastructure.service.exception.ReportException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

/**
 * @author elielcena
 *
 */
public class ExportPdf {

	public static byte[] generate(String path, Map<String, Object> parameters, JRDataSource dataSource) {
		try {
			File file = ResourceUtils.getFile("classpath:" + path);

			var inputStream = new FileInputStream(file);

			parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));

			var jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);

			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			throw new ReportException("Unable to generate report", e);
		}
	}

}
