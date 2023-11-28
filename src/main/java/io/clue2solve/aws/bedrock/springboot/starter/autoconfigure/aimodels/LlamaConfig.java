package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels;

import io.clue2solve.aws.bedrock.springboot.starter.config.LlamaProperties;
import io.clue2solve.aws.bedrock.springboot.starter.service.BedrockService;
import io.clue2solve.aws.bedrock.springboot.starter.service.impl.LlamaService;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Llama
 */
@Configuration
@ConditionalOnProperty(name = "aws.bedrock.model.llama.id", havingValue = "meta.llama2-13b-chat-v1")
public class LlamaConfig {

	/**
	 * Bean for LlamaService
	 * @param client BedrockRuntimeClient
	 * @param properties LlamaProperties
	 * @return BedrockService
	 */
	@Bean(name = "llamaService")
	public BedrockService llamaService(BedrockRuntimeClient client, LlamaProperties properties) {
		return new LlamaService(client, properties);
	}

}