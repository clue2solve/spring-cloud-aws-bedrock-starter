package io.clue2solve.aws.bedrock.springboot.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Sample perperties aws.bedrock.model.jurassic.modelId=ai21.j2-mid-v1
 * aws.bedrock.model.jurassic.prompt=Human: Assistant:
 * aws.bedrock.model.jurassic.maxTokens=200 aws.bedrock.model.jurassic.temperature=0.5
 */
@ConfigurationProperties(prefix = "aws.bedrock.model.jurassic")
public record JurassicProperties(String modelId, String prompt, int maxTokens, double temperature) {
	public JurassicProperties() {
		this("ai21.j2-mid-v1", "Human: Assistant:", 200, 0.5);
	}
}