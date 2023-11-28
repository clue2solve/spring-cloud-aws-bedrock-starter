package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels;

import io.clue2solve.aws.bedrock.springboot.starter.config.ClaudeProperties;
import io.clue2solve.aws.bedrock.springboot.starter.service.BedrockService;
import io.clue2solve.aws.bedrock.springboot.starter.service.impl.ClaudeService;
import org.springframework.context.annotation.Conditional;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Conditional(OnClaude.class)
public class ClaudeConfig {

	@Bean(name = "claudeService")
	public BedrockService claudeService(BedrockRuntimeClient client, ClaudeProperties properties) {
		return new ClaudeService(client, properties);
	}

}