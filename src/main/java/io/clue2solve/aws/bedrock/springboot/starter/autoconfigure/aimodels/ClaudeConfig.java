package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels;

import io.clue2solve.aws.bedrock.springboot.starter.config.ClaudeProperties;
import io.clue2solve.aws.bedrock.springboot.starter.service.ClaudeService;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "aws.bedrock.model.id", havingValue = "anthropic.claude-v2")
public class ClaudeConfig {

    @Bean
    public ClaudeService claudeService(BedrockRuntimeClient client, ClaudeProperties properties) {
        return new ClaudeService(client, properties);
    }
}