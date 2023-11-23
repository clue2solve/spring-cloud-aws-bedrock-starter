package io.clue2solve.aws.bedrock.springboot.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "aws.bedrock.model.claude")
public record ClaudeProperties(String modelId, String prompt, double temperature, double topP, int topK, int maxTokensToSample, List<String> stopSequences) {}