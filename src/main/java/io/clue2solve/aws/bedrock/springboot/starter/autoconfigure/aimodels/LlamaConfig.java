package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels;

import io.clue2solve.aws.bedrock.springboot.starter.config.LlamaProperties;
import io.clue2solve.aws.bedrock.springboot.starter.service.LlamaService;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "aws.bedrock.model.id", havingValue = "llama2")
public class LlamaConfig {

    @Bean
    public LlamaService llamaService(BedrockRuntimeClient client, LlamaProperties properties) {
        return new LlamaService(client, properties);
    }
}