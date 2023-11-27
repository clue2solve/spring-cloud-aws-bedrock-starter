package io.clue2solve.aws.bedrock.springboot.starter.config;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Validated
@ConfigurationProperties(prefix = "aws.bedrock.model.llama")
public record LlamaProperties(@Pattern(regexp = "meta.llama2-13b-chat-v1") String id,
		@DecimalMin("0.0") @DecimalMax("1.0") Double topP, @DecimalMin("0.0") @DecimalMax("1.0") Double temperature,
		@Min(1) @Max(4000) Integer maxTokens) {

	public LlamaProperties(String id, Double topP, Double temperature, Integer maxTokens) {
		this.id = Optional.ofNullable(id).orElse("meta.llama2-13b-chat-v1");
		this.temperature = Optional.ofNullable(temperature).orElse(0.5);
		this.topP = Optional.ofNullable(topP).orElse(0.9);
		this.maxTokens = Optional.ofNullable(maxTokens).orElse(100);
	}
}