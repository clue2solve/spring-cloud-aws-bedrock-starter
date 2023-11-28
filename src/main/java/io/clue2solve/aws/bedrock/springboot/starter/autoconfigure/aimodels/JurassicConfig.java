package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels;

import io.clue2solve.aws.bedrock.springboot.starter.config.JurassicProperties;
import io.clue2solve.aws.bedrock.springboot.starter.service.BedrockService;
import io.clue2solve.aws.bedrock.springboot.starter.service.impl.JurassicService;
import org.springframework.context.annotation.Conditional;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Jurassic
 */
@Configuration
@Conditional(OnJurassic.class)
public class JurassicConfig {

	/**
	 * Bean for JurassicService
	 * @param client BedrockRuntimeClient
	 * @param properties JurassicProperties
	 * @return BedrockService
	 */
	@Bean(name = "jurassicService")
	public BedrockService jurassicService(BedrockRuntimeClient client, JurassicProperties properties) {
		return new JurassicService(client, properties);
	}

}