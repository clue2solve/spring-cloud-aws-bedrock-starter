package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels;

import io.clue2solve.aws.bedrock.springboot.starter.config.JurassicProperties;
import io.clue2solve.aws.bedrock.springboot.starter.service.JurassicService;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "aws.bedrock.model.id", havingValue = "jurassic2")
public class JurassicConfig {

    @Bean
    public JurassicService jurassicService(BedrockRuntimeClient client, JurassicProperties properties) {
        return new JurassicService(client, properties);
    }
}