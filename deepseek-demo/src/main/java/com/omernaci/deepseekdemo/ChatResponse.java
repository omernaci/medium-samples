package com.omernaci.deepseekdemo;

import java.util.List;

public record ChatResponse(List<Choice> choices) {}
