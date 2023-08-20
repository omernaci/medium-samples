package com.omernaci.task.service;

import com.omernaci.task.service.dto.TaskDto;
import java.util.List;

public interface TaskService {

    List<TaskDto> findAllTasks();

    TaskDto createTask(TaskDto taskDto);

    TaskDto updateTaskStatus(Long taskId, TaskDto updatedTaskDto);

}
