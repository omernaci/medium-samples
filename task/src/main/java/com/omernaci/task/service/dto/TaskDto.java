package com.omernaci.task.service.dto;

import com.omernaci.task.persistence.entity.TaskStatus;

public record TaskDto(Long id, String title, String description, TaskStatus status) { }
