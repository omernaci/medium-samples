package com.omernaci.task.api;

import com.omernaci.task.service.TaskService;
import com.omernaci.task.service.dto.TaskDto;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> findAllTasks() {
        List<TaskDto> taskDtoList = taskService.findAllTasks();
        return new ResponseEntity<>(taskDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto createdTask = taskService.createTask(taskDto);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTaskStatus(@PathVariable Long taskId, @RequestBody TaskDto updatedTaskDto) {
        TaskDto updatedTask = taskService.updateTaskStatus(taskId, updatedTaskDto);
        return new ResponseEntity<>(updatedTask, updatedTask != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
