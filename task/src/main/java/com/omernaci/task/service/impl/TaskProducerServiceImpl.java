package com.omernaci.task.service.impl;

import com.omernaci.task.service.TaskProducerService;
import com.omernaci.task.service.dto.TaskStatusUpdateMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class TaskProducerServiceImpl implements TaskProducerService {

    private final RabbitTemplate rabbitTemplate;

    public TaskProducerServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendTaskStatusUpdateMessage(TaskStatusUpdateMessage message) {
        rabbitTemplate.convertAndSend("notificationRoutingKey", message);
    }

}
