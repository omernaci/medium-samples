package com.omernaci.task.api;

import com.omernaci.task.template.CsvReportGenerator;
import com.omernaci.task.template.PdfReportGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	public StreamingResponseBody exportCsvReport() {
		byte[] csvReport = csvReportGenerator.generateReport();

		return outputStream -> {
			outputStream.write(csvReport);
			outputStream.flush();
		};
	}

}
