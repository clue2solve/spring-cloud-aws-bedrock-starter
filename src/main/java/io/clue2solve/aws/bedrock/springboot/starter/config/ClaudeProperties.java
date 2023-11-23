package io.clue2solve.aws.bedrock.springboot.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

@ConfigurationProperties(prefix = "aws.bedrock.model.claude")
public record ClaudeProperties(String modelId, String prompt, double temperature, double topP, int topK,
                               int maxTokensToSample, List<String> stopSequences) {
    public ClaudeProperties() {
        this("anthropic.claude-v2",
                "defaultPrompt",
                0.5,
                0.5,
                50,
                100,
                Collections.emptyList());
    }
}