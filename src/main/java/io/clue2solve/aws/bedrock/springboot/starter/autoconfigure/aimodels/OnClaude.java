package io.clue2solve.aws.bedrock.springboot.starter.autoconfigure.aimodels;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

class OnClaude extends AnyNestedCondition {

	OnClaude() {
		super(ConfigurationPhase.PARSE_CONFIGURATION);
	}

	@ConditionalOnProperty(name = "aws.bedrock.model.claude.id", havingValue = "anthropic.claude-v2")
	static class OnClaudeV2 {

	}

	@ConditionalOnProperty(name = "aws.bedrock.model.claude.id", havingValue = "anthropic.claude-v1")
	static class OnClaudeV1 {

	}

	@ConditionalOnProperty(name = "aws.bedrock.model.claude.id", havingValue = "anthropic.claude-instant-v1")
	static class OnClaudeInstantV1 {

	}

}