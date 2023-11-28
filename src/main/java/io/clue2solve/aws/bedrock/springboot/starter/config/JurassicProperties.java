package io.clue2solve.aws.bedrock.springboot.starter.config;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

/**
 * Sample properties aws.bedrock.model.jurassic.modelId=ai21.j2-mid-v1
 * aws.bedrock.model.jurassic.prompt=Human: Assistant:
 * aws.bedrock.model.jurassic.maxTokens=200 aws.bedrock.model.jurassic.temperature=0.5
 *
 * @ see https://docs.ai21.com/reference/j2-complete-api-ref
 */
@Validated
@ConfigurationProperties(prefix = "aws.bedrock.model.jurassic")
public record JurassicProperties(@Pattern(
		/**
		 * Model ID
		 */
		regexp = "ai21.j2-grande-instruct|ai21.j2-jumbo-instruct|ai21.j2-mid|ai21.j2-mid-v1|ai21.j2-ultra|ai21.j2-ultra-v1") String id,
		@Nullable String prePrompt, @Min(1) @Max(8192) Integer maxTokens,
		@DecimalMin("0.0") @DecimalMax("1.0") Double temperature) {

	/**
	 * Constructor
	 * @param id String
	 * @param prePrompt String
	 * @param maxTokens Integer
	 * @param temperature Double
	 */
	public JurassicProperties(String id, String prePrompt, Integer maxTokens, Double temperature) {
		this.id = Optional.ofNullable(id).orElse("ai21.j2-mid-v1");
		this.prePrompt = Optional.ofNullable(prePrompt).orElse("Human: ");
		this.temperature = Optional.ofNullable(temperature).orElse(0.5);
		this.maxTokens = Optional.ofNullable(maxTokens).orElse(200);
	}
}
