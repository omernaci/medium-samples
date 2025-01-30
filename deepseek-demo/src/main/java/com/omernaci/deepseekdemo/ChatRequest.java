package com.omernaci.deepseekdemo;

import java.util.List;

public record ChatRequest(
        String model,
        List<Message> messages,
        boolean stream
) {}
