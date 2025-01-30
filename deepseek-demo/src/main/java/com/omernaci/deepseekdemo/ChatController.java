package com.omernaci.deepseekdemo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final DeepSeekApiClient deepSeekApiClient;

    public ChatController(DeepSeekApiClient deepSeekApiClient) {
        this.deepSeekApiClient = deepSeekApiClient;
    }

    @GetMapping("/ask")
    public ChatResponse chat(@RequestParam String message) {
        return deepSeekApiClient.sendMessage(message);
    }

}
