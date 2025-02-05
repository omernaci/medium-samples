package com.omernaci.deepseekdemo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class DeepSeekChatService {

    private final ChatClient chatClient;

    public DeepSeekChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String userMessage) {
        return chatClient.call(userMessage).getResult().getOutput().getContent();
    }
}
