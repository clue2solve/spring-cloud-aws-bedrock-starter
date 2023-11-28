package io.clue2solve.aws.bedrock.springboot.starter.config;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Sample properties
 * ws.bedrock.model.titan.id=amazon.titan-text-express-v1
 * aws.bedrock.model.titan.temperature=0.5
 * aws.bedrock.model.titan.topP=0.9
 * aws.bedrock.model.titan.maxTokens=100
 * aws.bedrock.model.titan.stopSequences=Human:,
 * Assistant:
 *
 * @see <a href="https://docs.aws.amazon.com/bedrock/latest/userguide/model-parameters-titan.html">...</a>
 */
@Validated
@ConfigurationProperties(prefix = "aws.bedrock.model.titan")
public record TitanProperties(@Pattern(
		regexp = "amazon.titan-tg1-large|amazon.titan-tg1-medium|amazon.titan-embed-g1-text-02|amazon.titan-text-lite-v1|amazon.titan-text-express-v1|amazon.titan-embed-text-v1") String id,
		@DecimalMin("0.0") @DecimalMax("1.0") Double temperature, @DecimalMin("0.0") @DecimalMax("1.0") Double topP,
		@Min(1) @Max(8192) Integer maxTokens, @Nullable List<String> stopSequences) {

	/**
	 * Constructor
	 * @param id String
	 * @param temperature Double
	 * @param topP Double
	 * @param maxTokens Integer
	 * @param stopSequences List<String>
	 */
	public TitanProperties(String id, Double temperature, Double topP, Integer maxTokens, List<String> stopSequences) {
		this.id = Optional.ofNullable(id).orElse("amazon.titan-text-express-v1");
		this.temperature = Optional.ofNullable(temperature).orElse(0.0);
		this.topP = Optional.ofNullable(topP).orElse(1.0);
		this.maxTokens = Optional.ofNullable(maxTokens).orElse(4096);
		this.stopSequences = Optional.ofNullable(stopSequences).orElse(Collections.emptyList());
	}
}