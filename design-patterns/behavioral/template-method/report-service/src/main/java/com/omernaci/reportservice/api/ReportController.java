package com.omernaci.reportservice.api;

import com.omernaci.reportservice.generator.CsvReportGenerator;
import com.omernaci.reportservice.generator.PdfReportGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/reports")
public class ReportController {

	private final PdfReportGenerator pdfReportGenerator;
	private final CsvReportGenerator csvReportGenerator;

	public ReportController(PdfReportGenerator pdfReportGenerator,
							CsvReportGenerator csvReportGenerator) {
		this.pdfReportGenerator = pdfReportGenerator;
		this.csvReportGenerator = csvReportGenerator;
	}

	@GetMapping("/pdf")
	public StreamingResponseBody exportPdfReport() {
		byte[] pdfReport = pdfReportGenerator.generateReport();

		return outputStream -> {
			outputStream.write(pdfReport);
			outputStream.flush();
		};
	}

	@GetMapping("/csv")
	public StreamingResponseBody exportCsvReport(HttpServletResponse response) {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"report.csv\"");

		return outputStream -> {
			byte[] reportContent = csvReportGenerator.generateReport();
			outputStream.write(reportContent);
		};
	}

}
