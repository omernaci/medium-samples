package com.omernaci.task.service;

import com.omernaci.task.service.dto.TaskStatusUpdateMessage;

public interface TaskProducerService {

    void sendTaskStatusUpdateMessage(TaskStatusUpdateMessage message);

}
