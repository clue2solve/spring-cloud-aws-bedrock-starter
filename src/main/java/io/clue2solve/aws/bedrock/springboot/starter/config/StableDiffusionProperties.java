package io.clue2solve.aws.bedrock.springboot.starter.config;

import jakarta.validation.constraints.*;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

/**
 * Sample properties aws.bedrock.model.stability.id=stability.stable-diffusion-xl
 * aws.bedrock.model.stability.promptStrength=10.0
 * aws.bedrock.model.stability.generationStep=30 aws.bedrock.model.stability.seed=75
 *
 * @see <a href=
 * "https://docs.aws.amazon.com/bedrock/latest/userguide/model-parameters-diffusion.html">...</a>
 */
@Validated
@ConfigurationProperties(prefix = "aws.bedrock.model.stability")
public record StableDiffusionProperties(@Pattern(
		regexp = "stability.stable-diffusion-xl|stability.stable-diffusion-xl-v0|stability.stable-diffusion-xl-v1") String id,
		@DecimalMin("0.0") @DecimalMax("30.0") Double promptStrength, @Min(10) @Max(150) Integer generationStep,
		@Min(1) @Max(100) Integer seed) {

	/**
	 * Constructor
	 * @param id the id
	 * @param promptStrength the prompt strength
	 * @param generationStep the generation step
	 * @param seed the seed
	 */
	public StableDiffusionProperties(String id, Double promptStrength, Integer generationStep, Integer seed) {
		this.id = Optional.ofNullable(id).orElse("stability.stable-diffusion-xl");
		this.promptStrength = Optional.ofNullable(promptStrength).orElse(10.0);
		this.generationStep = Optional.ofNullable(generationStep).orElse(30);
		this.seed = Optional.ofNullable(seed).orElse(75);
	}
}