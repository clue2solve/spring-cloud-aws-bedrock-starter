package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels;

import io.clue2solve.aws.bedrock.springboot.starter.config.StableDiffusionProperties;
import io.clue2solve.aws.bedrock.springboot.starter.service.BedrockService;
import io.clue2solve.aws.bedrock.springboot.starter.service.impl.StableDiffusionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;

@Configuration
@Conditional(OnStableDiffusion.class)
public class StableDiffusionConfig {

	@Bean(name = "stableDiffusionService")
	public BedrockService stableDiffusionService(BedrockRuntimeClient client, StableDiffusionProperties properties) {
		return new StableDiffusionService(client, properties);
	}

}