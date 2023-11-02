package com.omernaci.task.template;

import com.omernaci.task.persistence.entity.Task;

import java.util.List;

public abstract class ReportGenerator {

	public byte[] generateReport() {
		List<Task> data = fetchData();
		String formattedData = formatData(data);
		return generateReportContent(formattedData);
	}

	protected abstract List<Task> fetchData();

	protected abstract String formatData(List<Task> data);

	protected abstract byte[] generateReportContent(String formattedData);

}
