package com.omernaci.reportservice.generator;

import com.omernaci.reportservice.persistence.entity.Task;
import com.omernaci.reportservice.persistence.entity.TaskStatus;
import com.omernaci.reportservice.persistence.repository.TaskRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.List;

@Service
public class CsvReportGenerator extends AbstractReportGenerator {

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
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			CSVPrinter csvPrinter = new CSVPrinter(new PrintStream(byteArrayOutputStream), CSVFormat.DEFAULT);

			String[] lines = formattedData.split("\n");
			for (String line : lines) {
				String[] fields = line.split(",");
				csvPrinter.printRecord((Object[]) fields);
			}

			csvPrinter.flush();
			csvPrinter.close();

			return byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
