package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * Condition class for StableDiffusion
 */
class OnStableDiffusion extends AnyNestedCondition {

	/**
	 * Constructor
	 */
	OnStableDiffusion() {
		super(ConfigurationPhase.PARSE_CONFIGURATION);
	}

	/**
	 * Condition for StableDiffusion stability.stable-diffusion-xl
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.stability.id", havingValue = "stability.stable-diffusion-xl")
	static class OnStableDiffusionXL {

	}

	/**
	 * Condition for StableDiffusion stability.stable-diffusion-xl-v0
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.stability.id", havingValue = "stability.stable-diffusion-xl-v0")
	static class OnStableDiffusionXLV0 {

	}

	/**
	 * Condition for StableDiffusion stability.stable-diffusion-xl-v1
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.stability.id", havingValue = "stability.stable-diffusion-xl-v1")
	static class OnStableDiffusionXLV1 {

	}

}