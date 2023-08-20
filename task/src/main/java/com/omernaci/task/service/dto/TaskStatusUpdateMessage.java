package com.omernaci.task.service.dto;

import com.omernaci.task.persistence.entity.TaskStatus;

public record TaskStatusUpdateMessage(Long taskId, TaskStatus oldStatus, TaskStatus newStatus) {
}