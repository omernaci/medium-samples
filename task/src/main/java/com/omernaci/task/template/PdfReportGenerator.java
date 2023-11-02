package com.omernaci.task.template;

import com.omernaci.task.persistence.entity.Task;
import com.omernaci.task.persistence.repository.TaskRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfReportGenerator extends ReportGenerator {

	private final TaskRepository taskRepository;

	public PdfReportGenerator(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	protected List<Task> fetchData() {
		return taskRepository.findAll();
	}

	@Override
	protected String formatData(List<Task> data) {
		StringBuilder formattedData = new StringBuilder();
		formattedData.append("Task Title | Description | Status\n");

		for (Task task : data) {
			formattedData.append(task.getTitle())
					.append(" | ")
					.append(task.getDescription())
					.append(" | ")
					.append(task.getStatus())
					.append("\n");
		}

		return formattedData.toString();
	}

	@Override
	protected byte[] generateReportContent(String formattedData) {
		try (PDDocument document = new PDDocument()) {
			PDPage page = new PDPage();
			document.addPage(page);

			try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
				contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
				contentStream.beginText();
				contentStream.newLineAtOffset(100, 700);
				contentStream.showText("Generated PDF Report");
				contentStream.newLine();
				contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
				contentStream.showText(formattedData);
				contentStream.endText();
			}

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			document.save(outputStream);
			document.close();

			return outputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}
}
