package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * Condition class for Titan
 */
class OnTitan extends AnyNestedCondition {

	/**
	 * Constructor
	 */
	OnTitan() {
		super(ConfigurationPhase.PARSE_CONFIGURATION);
	}

	/**
	 * Condition for Titan amazon.titan-tg1-large
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.titan.id", havingValue = "amazon.titan-tg1-large")
	static class OnTitanLarge {

	}

	/**
	 * Condition for Titan amazon.titan-tg1-medium
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.titan.id", havingValue = "amazon.titan-tg1-medium")
	static class OnTitanMedium {

	}

	/**
	 * Condition for Titan Instant amazon.titan-embed-g1-text-02
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.titan.id", havingValue = "amazon.titan-embed-g1-text-02")
	static class OnTitanTextEmbeddingsV2 {

	}

	/**
	 * Condition for Titan Instant amazon.titan-text-lite-v1
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.titan.id", havingValue = "amazon.titan-text-lite-v1")
	static class OnTitanTextLiteV1 {

	}

	/**
	 * Condition for Titan Instant amazon.titan-text-express-v1
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.titan.id", havingValue = "amazon.titan-text-express-v1")
	static class OnTitanTextExpressV1 {

	}

	/**
	 * Condition for Titan Instant amazon.titan-embed-text-v1
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.titan.id", havingValue = "amazon.titan-embed-text-v1")
	static class OnTitanTextEmbeddingsV1 {

	}

}