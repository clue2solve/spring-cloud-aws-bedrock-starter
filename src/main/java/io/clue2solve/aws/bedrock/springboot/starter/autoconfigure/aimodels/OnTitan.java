package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

class OnTitan extends AnyNestedCondition {

	OnTitan() {
		super(ConfigurationPhase.PARSE_CONFIGURATION);
	}

	@ConditionalOnProperty(name = "aws.bedrock.model.titan.id", havingValue = "amazon.titan-tg1-large")
	static class OnTitanLarge {

	}

	@ConditionalOnProperty(name = "aws.bedrock.model.titan.id", havingValue = "amazon.titan-tg1-medium")
	static class OnTitanMedium {

	}

	@ConditionalOnProperty(name = "aws.bedrock.model.titan.id", havingValue = "amazon.titan-embed-g1-text-02")
	static class OnTitanTextEmbeddingsV2 {

	}

	@ConditionalOnProperty(name = "aws.bedrock.model.titan.id", havingValue = "amazon.titan-text-lite-v1")
	static class OnTitanTextLiteV1 {

	}

	@ConditionalOnProperty(name = "aws.bedrock.model.titan.id", havingValue = "amazon.titan-text-express-v1")
	static class OnTitanTextExpressV1 {

	}

	@ConditionalOnProperty(name = "aws.bedrock.model.titan.id", havingValue = "amazon.titan-embed-text-v1")
	static class OnTitanTextEmbeddingsV1 {

	}

}