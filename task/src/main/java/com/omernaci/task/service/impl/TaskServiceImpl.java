package com.omernaci.task.service.impl;

import com.omernaci.task.persistence.entity.Task;
import com.omernaci.task.persistence.entity.TaskStatus;
import com.omernaci.task.persistence.repository.TaskRepository;
import com.omernaci.task.service.TaskProducerService;
import com.omernaci.task.service.TaskService;
import com.omernaci.task.service.dto.TaskDto;
import com.omernaci.task.service.dto.TaskStatusUpdateMessage;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskProducerService taskProducerService;

    public TaskServiceImpl(TaskRepository taskRepository,
                           TaskProducerService taskProducerService) {
        this.taskRepository = taskRepository;
        this.taskProducerService = taskProducerService;
    }

    @Override
    public List<TaskDto> findAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
            .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getStatus()))
            .toList();
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Task task = new Task(taskDto.title(), taskDto.description(), taskDto.status());
        Task savedTask = taskRepository.save(task);
        return new TaskDto(savedTask.getId(), savedTask.getTitle(), savedTask.getDescription(), savedTask.getStatus());
    }

    @Override
    public TaskDto updateTaskStatus(Long taskId, TaskDto updatedTaskDto) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task != null) {
            TaskStatus oldStatus = task.getStatus();
            task.setStatus(updatedTaskDto.status());
            taskRepository.save(task);

            taskProducerService.sendTaskStatusUpdateMessage(new TaskStatusUpdateMessage(taskId, oldStatus, updatedTaskDto.status()));

            return new TaskDto(taskId, task.getTitle(), task.getDescription(), updatedTaskDto.status());
        }
        return null;
    }

}
