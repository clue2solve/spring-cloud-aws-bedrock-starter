package io.clue2solve.aws.bedrock.springboot.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "aws.bedrock.model.llama")
public record LlamaProperties(String modelId, String prompt, double temperature, int maxTokens, List<String> stopSequences) {}