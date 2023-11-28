package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels;

import io.clue2solve.aws.bedrock.springboot.starter.config.TitanProperties;
import io.clue2solve.aws.bedrock.springboot.starter.service.BedrockService;
import io.clue2solve.aws.bedrock.springboot.starter.service.impl.TitanService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;

/**
 * Configuration class for Titan
 */
@Configuration
@Conditional(OnTitan.class)
public class TitanConfig {

	/**
	 * Bean for TitanService
	 * @param client BedrockRuntimeClient
	 * @param properties TitanProperties
	 * @return BedrockService
	 */
	@Bean(name = "titanService")
	public BedrockService titanService(BedrockRuntimeClient client, TitanProperties properties) {
		return new TitanService(client, properties);
	}

}