package io.clue2solve.aws.bedrock.springboot.starter.config;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Sample properties aws.bedrock.model.llama2.modelId=meta.llama2-13b-chat-v1
 * aws.bedrock.model.llama2.topP=0.9 aws.bedrock.model.llama2.temperature=0.5
 * aws.bedrock.model.llama2.maxTokens=100
 *
 * @see <a href="https://docs.ai21.com/reference/llama2-complete-api-ref">...</a>
 */
@Validated
@ConfigurationProperties(prefix = "aws.bedrock.model.llama2")
public record LlamaProperties(@Pattern(regexp = "meta.llama2-13b-chat-v1") String id,
		@DecimalMin("0.0") @DecimalMax("1.0") Double topP, @DecimalMin("0.0") @DecimalMax("1.0") Double temperature,
		@Min(1) @Max(4000) Integer maxTokens) {

	/**
	 * Constructor
	 * @param id String
	 * @param topP Double
	 * @param temperature Double
	 * @param maxTokens Integer
	 */
	public LlamaProperties(String id, Double topP, Double temperature, Integer maxTokens) {
		this.id = Optional.ofNullable(id).orElse("meta.llama2-13b-chat-v1");
		this.temperature = Optional.ofNullable(temperature).orElse(0.5);
		this.topP = Optional.ofNullable(topP).orElse(0.9);
		this.maxTokens = Optional.ofNullable(maxTokens).orElse(100);
	}
}