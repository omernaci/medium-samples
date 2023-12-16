package com.omernaci.reportservice.generator;

import com.omernaci.reportservice.persistence.entity.Task;
import com.omernaci.reportservice.persistence.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfReportGenerator extends AbstractReportGenerator {
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
				contentStream.setFont(PDType1Font.HELVETICA, 12);
				contentStream.beginText();
				contentStream.newLineAtOffset(100, 700);
				contentStream.showText("Generated PDF Report");
				contentStream.newLine();
				contentStream.setFont(PDType1Font.HELVETICA, 12);
				contentStream.showText(formattedData.replace("\n", "").replace("\r", ""));
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
