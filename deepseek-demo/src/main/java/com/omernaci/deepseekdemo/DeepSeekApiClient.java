package com.omernaci.deepseekdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class DeepSeekApiClient {

    private final RestClient restClient;

    public DeepSeekApiClient(@Value("${deepseek.api.key}") String apiKey) {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.deepseek.com/chat/completions")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public ChatResponse sendMessage(String userMessage) {
        String jsonPayload = String.format("""
            {
              "model": "deepseek-chat",
              "messages": [
                {"role": "system", "content": "You are a helpful assistant."},
                {"role": "user", "content": "%s"}
              ],
              "stream": false
            }
            """, userMessage);
        return restClient.post()
                .body(jsonPayload)
                .retrieve()
                .body(ChatResponse.class);
    }

}
