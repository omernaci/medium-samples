package com.omernaci.asyncexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/execute-async")
    public String executeAsync() {
        asyncService.asyncMethod();
        return "Async method is being executed!";
    }

}