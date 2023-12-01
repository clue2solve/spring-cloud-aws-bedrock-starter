package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

class OnLlama extends AnyNestedCondition {

	/**
	 * Constructor
	 */
	OnLlama() {
		super(ConfigurationPhase.PARSE_CONFIGURATION);
	}

	/**
	 * Condition for Llama meta.llama2-13b-chat-v1
	 */
	@ConditionalOnProperty(name = "aws.bedrock.model.llama2.id", havingValue = "meta.llama2-13b-chat-v1")
	static class OnLlama213bChatV1 {

	}

}
