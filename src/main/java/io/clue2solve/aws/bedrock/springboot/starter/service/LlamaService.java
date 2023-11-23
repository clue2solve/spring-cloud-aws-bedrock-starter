package io.clue2solve.aws.bedrock.springboot.starter.service;

import io.clue2solve.aws.bedrock.springboot.starter.config.LlamaProperties;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;

public class LlamaService {
    private final BedrockRuntimeClient client;
    private final LlamaProperties properties;

    public LlamaService(BedrockRuntimeClient client, LlamaProperties properties) {
        this.client = client;
        this.properties = properties;
    }

    // Add methods to interact with the llama model here
}