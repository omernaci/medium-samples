package com.omernaci.task.template;

import com.omernaci.task.persistence.entity.Task;
import com.omernaci.task.persistence.entity.TaskStatus;
import com.omernaci.task.persistence.repository.TaskRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@Service
public class CsvReportGenerator extends ReportGenerator {

	private final TaskRepository taskRepository;

	public CsvReportGenerator(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	protected List<Task> fetchData() {
		return taskRepository.findAllByStatus(TaskStatus.IN_PROGRESS);
	}

	@Override
	protected String formatData(List<Task> data) {
		StringBuilder formattedData = new StringBuilder();
		formattedData.append("Task Title,Description,Status\n");

		for (Task task : data) {
			formattedData.append(task.getTitle())
					.append(",")
					.append(task.getDescription())
					.append(",")
					.append(task.getStatus())
					.append("\n");
		}

		return formattedData.toString();
	}

	@Override
	protected byte[] generateReportContent(String formattedData) {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			try (CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(outputStream), CSVFormat.DEFAULT)) {
				String[] lines = formattedData.split("\n");
				for (String line : lines) {
					csvPrinter.printRecord((Object) line.split(","));
				}
			}
			return outputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}

}
