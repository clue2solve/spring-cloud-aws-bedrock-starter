package io.clue2solve.aws.bedrock.springboot.starter.config;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Validated
@ConfigurationProperties(prefix = "aws.bedrock.model.claude")
public record ClaudeProperties(
		@Pattern(regexp = "anthropic.claude-instant-v1|anthropic.claude-v1|anthropic.claude-v2") String id,
		@Nullable String prePrompt, @Nullable String postPrompt,
		@DecimalMin("0.1") @DecimalMax("1.0") Double temperature, @Min(1) @Max(100000) Integer maxTokens,
		@Nullable List<String> stopSequences) {

	public ClaudeProperties(String id, String prePrompt, String postPrompt, Double temperature, Integer maxTokens,
			List<String> stopSequences) {
		this.id = Optional.ofNullable(id).orElse("anthropic.claude-v2");
		this.prePrompt = Optional.ofNullable(prePrompt).orElse("Human: ");
		this.postPrompt = Optional.ofNullable(postPrompt).orElse("Assistant: ");
		this.temperature = Optional.ofNullable(temperature).orElse(0.5);
		this.maxTokens = Optional.ofNullable(maxTokens).orElse(100);
		this.stopSequences = Optional.ofNullable(stopSequences).orElse(Collections.emptyList());
	}
}